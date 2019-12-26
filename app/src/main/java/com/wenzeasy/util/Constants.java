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

    //public static String CLIENT_ID = "r9MSiKjfBghqvEsUBQNeypNf1hAi2PDS";
    public static String CLIENT_ID = "CxiYQjAxC5qUwgA5Df10ARcFgCGQuUTU";
    //public static String CLIENT_SECRET = "rewtuGqP7ngTIUG2";
    public static String CLIENT_SECRET = "Jl8gzPIuNSAsLm3S";
    public static String ACCESS_TOKEN = "Basic Q3hpWVFqQXhDNXFVd2dBNURmMTBBUmNGZ0NHUXVVVFU6Smw4Z3pQSXVOU0FzTG0zUw==";
    //public static String ACCESS_TOKEN = "cjlNU2lLamZCZ2hxdkVzVUJRTmV5cE5mMWhBaTJQRFM6cmV3dHVHcVA3bmdUSVVHMg==";

    public static String SENDER_ADDRESS = "tel:+243895026521";
    public static String SENDER_NAME = "Wenzeasy";

    public static int dpToPx(float dp, Context context) {
        return dpToPx(dp, context.getResources());
    }

    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    public static int pxToDp(float px, Context context) {
        float dp = px / context.getResources().getDisplayMetrics().density;
        return (int) dp;
    }

    public static void showOnUnknownError(View view, View.OnClickListener onClickListener) {
       /* Snacky.builder()
                .setView(view)
                .setText("Impossible de se connecter au serveur.")
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText("Réessayez")
                .setActionClickListener(onClickListener)
                .error()
                .show();*/
    }

    public static void showOnTimeoutErro(View view, View.OnClickListener onClickListener) {
        /*Snacky.builder()
                .setView(view)
                .setText("Le délais s'est t'écouler.")
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText("Réessayez")
                .setActionClickListener(onClickListener)
                .error()
                .show();*/
    }

    public static void showOnNetworkError(View view, View.OnClickListener onClickListener) {
        /*Snacky.builder()
                .setView(view)
                .setText("Aucune connexion réseau. Réessayez plus tard.")
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText("Réessayez")
                .setActionClickListener(onClickListener)
                .error()
                .show();*/
    }

    public static void showAllertSucces(View view, String message, String action, View.OnClickListener onClickListener) {
        /*Snacky.builder()
                .setView(view)
                .setText(message)
                .setDuration(Snacky.LENGTH_INDEFINITE)
                .setActionText(action)
                .setActionClickListener(onClickListener)
                .success()
                .show();*/
    }

    public static String getOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) WenzeasyApplication.getWenzeasyContext().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimOperatorName();
    }

    public static Uri ussdToCallableUri(String ussd) {
        String uriString = "";

        if (!ussd.startsWith("tel:"))
            uriString += "tel:";

        for (char c : ussd.toCharArray()) {
            if (c == '#')
                uriString += Uri.encode("#");
            else
                uriString += c;
        }

        return Uri.parse(uriString);
    }

//    public static String generatePhoneNumber(String phone) {
//        String codePhone = UserPrefencesManager.getCurrentUser().getTelephone().substring(0, 3);
//        String destinatairePhone;
//        String recipient = phone.replace(" ", "");
//
//        int stringLength = recipient.length();
//
//        if (recipient.length() == 9)
//            destinatairePhone = codePhone + recipient;
//        else if (recipient.length() == 10)
//            destinatairePhone = codePhone + recipient.substring(1, stringLength);
//        else
//            destinatairePhone = recipient;
//
//        LogCat.e(destinatairePhone);
//
//        return destinatairePhone;
//    }

    public static String generatePhoneCode(boolean plus, String phone, String code) {

        return plus ? String.format("+%s%s", code, phone) : String.format("%s%s", code, phone);
    }

    public static String generatePhoneWithoutCode(boolean plus, String phone) {
        return plus ? phone.substring(4, phone.length()) : phone.substring(3, phone.length());
    }

    public static int generateCode(boolean plus, String phone) {
        return plus ? Integer.valueOf(phone.substring(1, 4)) : Integer.valueOf(phone.substring(0, 3));
    }

//    public static void generateQRcode(String data, ImageView imageView, WindowManager manager) {
//        Display display = manager.getDefaultDisplay();
//        Point point = new Point();
//        display.getSize(point);
//        int width = point.x;
//        int height = point.y;
//        int smallerDimension = width < height ? width : height;
//        smallerDimension = smallerDimension * 3 / 5;
//
//        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
//        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, smallerDimension);
//        try {
//            imageView.setImageBitmap(qrgEncoder.encodeAsBitmap());
//        } catch (WriterException e) {
//            LogCat.e(e.toString());
//        }
//    }

    public static boolean containsIgnoreCase(String haystack, String needle) {
        if (needle.equals(""))
            return true;

        if (haystack == null || haystack.equals(""))
            return false;

        Pattern pattern = Pattern.compile(needle, Pattern.CASE_INSENSITIVE + Pattern.LITERAL);
        Matcher matcher = pattern.matcher(haystack);

        return matcher.find();
    }

//    public static void initStatusBar(Activity context) {
//        Window window = context.getWindow();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            SystemBarTintManager tintManager = new SystemBarTintManager(context);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setTintColor(ContextCompat.getColor(context, R.color.md_light_green_700));
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
//    }

//    public static void showAllertNoAction(View viewById, String s) {
//        Snacky.builder()
//                .setView(viewById)
//                .setText(s)
//                .setDuration(Snacky.LENGTH_LONG)
//                .success()
//                .show();
//    }

    public static String truncFloat(float number) {
        DecimalFormat df = new DecimalFormat("#######.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(dfs);
        return df.format(BigDecimal.valueOf(number).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
    }
}
