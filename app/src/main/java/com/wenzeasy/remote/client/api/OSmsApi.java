package com.wenzeasy.remote.client.api;

import android.util.Log;

import com.github.ismaeltoe.osms.library.Osms;
import com.github.ismaeltoe.osms.library.entities.CredentialsEntity;
import com.github.ismaeltoe.osms.library.entities.messaging.MessageEntity;
import com.github.ismaeltoe.osms.library.entities.messaging.OutboundSMSMessageRequest;
import com.github.ismaeltoe.osms.library.entities.messaging.OutboundSMSTextMessage;
import com.github.ismaeltoe.osms.library.services.CredentialsService;
import com.github.ismaeltoe.osms.library.services.MessagingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wenzeasy.util.Constants;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.wenzeasy.util.Constants.SENDER_ADDRESS;
import static com.wenzeasy.util.Constants.SENDER_NAME;

public class OSmsApi {

    private static final String TAG = "SmsAuth";

    public static void sendMessage(final String phoneNumber, final OnSmsAuthListener onSmsAuthListener){

        final Osms osms = new Osms(Constants.CLIENT_ID, Constants.CLIENT_SECRET);
        osms.setAccessToken(Constants.ACCESS_TOKEN);

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        CredentialsService credentialsService = osms.credentials();

        credentialsService.getAccessToken("client_credentials", new Callback<CredentialsEntity>() {
            @Override
            public void success(CredentialsEntity credentialsEntity, Response response) {
                osms.setAccessToken(credentialsEntity.getAccessToken());
                //onSmsAuthListener.finish(gson.toJson(credentialsEntity));
                Log.d(TAG, "success credentials: "+gson.toJson(credentialsEntity));

                MessagingService messagingService = osms.messaging();

                OutboundSMSTextMessage OUTBOUND_SMS_TEXT_MESSAGE = new OutboundSMSTextMessage(ServiceGenerator.createSmsService());

                OutboundSMSMessageRequest OUTBOUND_SMS_MESSAGE_REQUEST = new OutboundSMSMessageRequest(
                        "tel:"+phoneNumber,
                        OUTBOUND_SMS_TEXT_MESSAGE,
                        SENDER_ADDRESS,
                        SENDER_NAME
                );

                MessageEntity MESSAGE_ENTITY = new MessageEntity(OUTBOUND_SMS_MESSAGE_REQUEST);
                messagingService.sendMessage(
                        SENDER_ADDRESS,
                        MESSAGE_ENTITY,
                        new Callback<MessageEntity>() {
                            @Override
                            public void success(MessageEntity messageEntity, Response response) {
                                onSmsAuthListener.finish(gson.toJson(messageEntity));

                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {
                                Log.e(TAG, "failure: ", retrofitError);
                                onSmsAuthListener.failure(retrofitError.getMessage(),retrofitError);
                            }
                        }
                );
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                //accessTokenView.setText(retrofitError.getMessage());
                Log.e(TAG, retrofitError.getMessage());
                Log.e(TAG, "failure credential: ",retrofitError);
            }
        });
    }

    public interface OnSmsAuthListener {
        void finish(String s);
        void failure(String message, Throwable t);
    }
}
