package com.wenzeasy.app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.paypal.android.sdk.payments.PayPalConfiguration;

import static com.wenzeasy.BuildConfig.PROD_BASE_URL;
import static com.wenzeasy.BuildConfig.TEST_BASE_URL;

/**
 * Created by davidkazad on 25/07/2018.
 */

public class Configuration {

    public static final String PAYPAL_CLIENT_ID_SANDBOX = "AV3XFYFqdLx8CRhV6H7DwdV6FetDdm4BCYL6Ysj6rtJKLZRnHgXHIfJhwxtjNCYQh0bNwa6IA8vmpgKj";
    public static final String PAYPAL_CLIENT_ID_PRODUCTION2 = "AbBrvX9IcljbVCDUZwVjvao4elqkpvRMuoZSEMMi0zuOPpI3wH4ruajgqdCUhuFye8Zh_eCxryfO4DSp";
    public static final String PAYPAL_CLIENT_ID_PRODUCTION = "AWrrGaLHvLfc7wnKLntVd11hDLZPAgovKU1kOxkN6g_Z4pyx3eeGTaQM92gc_SOSGWQb34KJK2N_a229";

    public static boolean VERSION_PRODUCTION = false;

    public static DatabaseReference firebaseReferencce =  FirebaseDatabase.getInstance().getReference("Delivery");

    public static final int PAYPAL_REQUEST_CODE = 7171;

    public static PayPalConfiguration PAYPAL_CONFIGURATION = new PayPalConfiguration()
            .environment((PayPalConfiguration.ENVIRONMENT_PRODUCTION)) //sandbox for development test
            .clientId(PAYPAL_CLIENT_ID_PRODUCTION);

    public static String getBaseUrl() {
        if (VERSION_PRODUCTION)
            return PROD_BASE_URL;
        else return TEST_BASE_URL;

    }
}
