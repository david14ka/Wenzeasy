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

import com.wenzeasy.BuildConfig;
import com.wenzeasy.models.User;
import com.wenzeasy.remote.client.response.BaseResponse;
import com.wenzeasy.remote.client.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Sets up a JAVA interface to the Maishapay API
 */
public interface WenzeasyAPI {

    @FormUrlEncoded
    @POST(BuildConfig.END_POINT)
    Observable<User> login(@Field("header") String ent,
                           @Field("phone") String telephone,
                           @Field("password") String pin);

    @FormUrlEncoded
    @POST(BuildConfig.END_POINT)
    Observable<String> serverTest(@Field("header") String server_test);
}
