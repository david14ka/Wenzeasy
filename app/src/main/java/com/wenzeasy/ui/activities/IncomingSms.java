package com.wenzeasy.ui.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.wenzeasy.MainActivity;
import com.wenzeasy.util.Constants;

public class IncomingSms extends BroadcastReceiver {
     
    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();
     
    public void onReceive(Context context, Intent intent) {
     
        // Retrieves a map of extended data from the intent.
        Log.d("smsReceiver", "onReceive: toast");
//        final Bundle bundle = intent.getExtras();
//
//        try {
//
//            if (bundle != null) {
//
//                final Object[] pdusObj = (Object[]) bundle.get("pdus");
//                Log.i("SmsReceiver", "senderNum: message: " + bundle.toString());
//
//                for (int i = 0; i < pdusObj.length; i++) {
//
//                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
//                    //String phoneNumber = currentMessage.getDisplayOriginatingAddress();
//
//                   // String senderNum = phoneNumber;
//                    String message = currentMessage.getDisplayMessageBody();
//
//                    Log.i("SmsReceiver", "messageBody: " + message);
//
//                    if (message.contains(Prefs.getString(Constants.OSMS_VERIFICATION_CODE,"nop"))){
//                        Log.i("SmsReceiver",  "; message: " + message);
//                        Prefs.putBoolean(Constants.OSMS_CODE_VERIFIED, true);
//                        context.startActivity(new Intent(context, MainActivity.class));
//
//                    }
//                   // Show Alert
//                    int duration = Toast.LENGTH_LONG;
//                    Toast toast = Toast.makeText(context,
//                                 "senderNum: " + ", message: " + message, duration);
//                    toast.show();
//
//                } // end for loop
//              } // bundle is null
//
//        } catch (Exception e) {
//            Log.e("SmsReceiver", "Exception smsReceiver" +e);
//
//        }
    }    
}