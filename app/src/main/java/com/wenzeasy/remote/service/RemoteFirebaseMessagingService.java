package com.wenzeasy.remote.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.wenzeasy.R;
import com.wenzeasy.remote.service.helper.AppNotificationHelper;
import com.wenzeasy.remote.service.model.Common;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.Map;
import java.util.Random;

import static com.mikepenz.iconics.Iconics.TAG;


public class RemoteFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow(remoteMessage);
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    private void scheduleJob() {

    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        Common.currentUserToken = token;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tokenReference = database.getReference("Token");
        Prefs.putString("Token",token);

        tokenReference.push().setValue(token);
    }

    private void handleNow(RemoteMessage remoteMessage) {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O)
            sendNotificationAPI26(remoteMessage,null);

        else
            sendNotification(remoteMessage, null);
    }

    private void sendNotification(RemoteMessage remoteMessage, Class clazz) {
        Map<String, String> notification = remoteMessage.getData();
        String title = notification.get("title");
        String content =notification.get("message");

        if (clazz != null){

            Intent intent = new Intent(this, clazz);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    intent, PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.wenzeasy)
                    .setContentTitle(content)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri)
                    .setContentIntent(pendingIntent);
            NotificationManager notificationManager =
                    (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,nBuilder.build());

        }
        else {
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.wenzeasy)
                    .setContentTitle(content)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri);

            NotificationManager notificationManager =
                    (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0,nBuilder.build());
        }
    }

    private void sendNotificationAPI26(RemoteMessage remoteMessage, Class clazz) {
        Map<String, String> notification = remoteMessage.getData();
        String title = notification.get("title");
        String content = notification.get("message");

        PendingIntent pendingIntent;
        AppNotificationHelper helper;
        Notification.Builder builder;

        if (clazz != null){

            Intent intent = new Intent(this, clazz);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pendingIntent = PendingIntent.getActivity(this,0,intent,
                    PendingIntent.FLAG_ONE_SHOT);
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            helper = new AppNotificationHelper(this);
            builder = helper.getNotificationBuilder(title,content,pendingIntent,defaultSoundUri);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                helper.getManager().notify(new Random().nextInt(), builder.build());
            }
        }
        else {
            Uri defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            helper = new AppNotificationHelper(this);
            builder = helper.getNotificationBuilder(title,content,defaultUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                helper.getManager().notify(new Random().nextInt(), builder.build());
            }
        }
    }
}
