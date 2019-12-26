package com.wenzeasy.remote.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.wenzeasy.MainActivity;
import com.wenzeasy.remote.service.helper.AppNotification;
import com.pixplicity.easyprefs.library.Prefs;



public class NotificationService extends Service {

    private static final String TAG= NotificationService.class.getName();

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");

        AppNotification.readNotification(this)
                .startActivity(MainActivity.class)
                .readFrom(Prefs.getString("phoneNumber",""));

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        /*isRunning = false;
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
        Log.i(TAG, "Service onDestroy");

        super.onDestroy();
    }
}
