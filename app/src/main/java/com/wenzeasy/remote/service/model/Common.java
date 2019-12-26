package com.wenzeasy.remote.service.model;

import com.google.firebase.iid.FirebaseInstanceId;
import com.pixplicity.easyprefs.library.Prefs;


public class Common {

    public static String currentUserToken = FirebaseInstanceId.getInstance().getToken();
    public static String currentUserPhone = Prefs.getString("phoneNumber","");
}
