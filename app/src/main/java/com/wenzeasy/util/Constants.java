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

package com.wenzeasy.util;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.View;

import com.wenzeasy.app.WenzeasyApplication;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * IntefaceOsms class
 */

public class Constants {

    public static final String MESSAGE_VERIFICATION_FAILURE = "Code OPT Incorrect,veuillez re-essayer svp! ";
    //public static String CLIENT_ID = "r9MSiKjfBghqvEsUBQNeypNf1hAi2PDS";
    public static String CLIENT_ID = "CxiYQjAxC5qUwgA5Df10ARcFgCGQuUTU";
    //public static String CLIENT_SECRET = "rewtuGqP7ngTIUG2";
    public static String CLIENT_SECRET = "Jl8gzPIuNSAsLm3S";
    public static String ACCESS_TOKEN = "Basic Q3hpWVFqQXhDNXFVd2dBNURmMTBBUmNGZ0NHUXVVVFU6Smw4Z3pQSXVOU0FzTG0zUw==";
    //public static String ACCESS_TOKEN = "cjlNU2lLamZCZ2hxdkVzVUJRTmV5cE5mMWhBaTJQRFM6cmV3dHVHcVA3bmdUSVVHMg==";

    public static String SENDER_ADDRESS = "tel:+243895026521";
    public static String SENDER_NAME = "Wenzeasy";


    public static String MESSAGE_VERFICATION_SUCCESS = "verfication effectuée avec succes!";
    public static String OSMS_VERIFICATION_CODE = "osms_token";
    public static String OSMS_CODE_VERIFIED = "osms_token";

}
