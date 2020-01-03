/*
 * Copyright 2018 Jonathan Monga.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wenzeasy.remote.client.api;

import android.util.JsonReader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pixplicity.easyprefs.library.Prefs;
import com.wenzeasy.app.WenzeasyApplication;
import com.wenzeasy.util.Constants;

import org.alfonz.utility.NetworkUtility;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Random;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Generates Maishapay API Service.
 */
public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String baseURL) {

        long cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(WenzeasyApplication.getWenzeasyContext().getCacheDir(), cacheSize);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();

                        if (NetworkUtility.isOnline(WenzeasyApplication.getWenzeasyContext()))
                            request = request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build();
                        else
                            request = request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build();

                        return chain.proceed(request);
                    }
                }).build();

        GsonBuilder gsonBuilder = new GsonBuilder()
                .setLenient()
                .excludeFieldsWithModifiers(Modifier.FINAL)
                .excludeFieldsWithModifiers(Modifier.STATIC);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(serviceClass);
    }

    public static String createNewSMS() {

        String token = createToken();

        String msg = "Your Wenzeasy verification phone number is: \nOPT- " + token;

        Prefs.putString(Constants.OSMS_VERIFICATION_CODE, token);

        return msg;
    }

    public static String createToken() {
        return String.valueOf(new Random().nextInt(99999));
    }
}