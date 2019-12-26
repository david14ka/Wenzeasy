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

package com.wenzeasy.remote.client;


import com.wenzeasy.app.Configuration;
import com.wenzeasy.models.User;
import com.wenzeasy.remote.client.api.WenzeasyAPI;
import com.wenzeasy.remote.client.api.ServiceGenerator;
import com.wenzeasy.remote.client.response.LoginResponse;

import io.reactivex.Observable;

import static com.wenzeasy.BuildConfig.LOGIN_PARAM;
import static com.wenzeasy.BuildConfig.PROD_BASE_URL;


/**
 * Wenzeasy client
 */

public final class WenzeasyClient {

    /**
     * new instance of {@link WenzeasyClient}
     */
    private static WenzeasyClient INSTANCE = null;

    private final WenzeasyAPI wenzeasyAPI;

    private WenzeasyClient() {
        this.wenzeasyAPI = ServiceGenerator.createService(WenzeasyAPI.class, Configuration.getBaseUrl());
    }

    /**
     * Skeleton method create an instance of {@link WenzeasyClient}
     */
    public static WenzeasyClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new WenzeasyClient();
        return INSTANCE;
    }

    /**
     * Login method.
     *
     * @return The Login response.
     */
    public Observable<User> login(String phoneNumber, String password) {
        return this.wenzeasyAPI.login(LOGIN_PARAM, phoneNumber, password);
    }

    public Observable<String> serverTest() {
        return this.wenzeasyAPI.serverTest("server_test");
    }

}
