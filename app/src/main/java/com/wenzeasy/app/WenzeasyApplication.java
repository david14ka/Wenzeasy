/*
 * Copyright (C) 2017 grandcentrix GmbH
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wenzeasy.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.StrictMode;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.github.ismaeltoe.osms.library.Osms;
import com.github.ismaeltoe.osms.library.services.CredentialsService;
import com.github.ismaeltoe.osms.library.services.MessagingService;
import com.wenzeasy.remote.client.WenzeasyClient;
import com.pixplicity.easyprefs.library.Prefs;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.wenzeasy.util.Constants.ACCESS_TOKEN;
import static com.wenzeasy.util.Constants.CLIENT_ID;
import static com.wenzeasy.util.Constants.CLIENT_SECRET;


public class WenzeasyApplication extends MultiDexApplication {

    private static WenzeasyApplication application;
    private WenzeasyClient wenzeasyClient;
    //private SoundManager mSoundManager;
    private Osms osms;
    private MessagingService messagingService;
    private CredentialsService credentialsService;

    @Override
    public void onCreate() {
        super.onCreate();

       /* final Fabric fabric = new Fabric.Builder(this)
                .kits(new Answers(), new Crashlytics())
                .debuggable(true)
                .build();

        Fabric.with(fabric);*/

        application = this;

        osms = new Osms(CLIENT_ID, CLIENT_SECRET);
        osms.setAccessToken(ACCESS_TOKEN);

        credentialsService = osms.credentials();
        messagingService = osms.messaging();

        wenzeasyClient = WenzeasyClient.getInstance();
        //mSoundManager = new SoundManager(application, SoundManager.PLAY_SINGLE);


        iniPreference();
        load();
    }

    public Osms getOsms() {
        return osms;
    }

    public CredentialsService getCredentialsService() {
        return credentialsService;
    }

    private void iniPreference() {
        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
    }

    @Override
    protected void attachBaseContext(Context base) {
        MultiDex.install(base);
        super.attachBaseContext(base);
    }

    public static WenzeasyApplication getWenzeasyContext() {
        return application;
    }

    public WenzeasyClient getWenzeasyClient() {
        return wenzeasyClient;
    }

    private void load() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                /*CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Light.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
                );*/

                AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe();
    }

//    public SoundManager getmSoundManager() {
//        return mSoundManager;
//    }

    public void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
