package com.wenzeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.wenzeasy.ui.activities.ConfirmPinActivity;
import com.wenzeasy.ui.activities.ConfirmPinActivity2;
import com.wenzeasy.ui.activities.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        },300);
    }
}
