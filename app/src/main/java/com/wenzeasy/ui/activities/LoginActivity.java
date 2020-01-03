package com.wenzeasy.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.wenzeasy.R;
import com.wenzeasy.models.User;
import com.wenzeasy.remote.client.api.OSmsApi;
import com.wenzeasy.remote.client.api.ServiceGenerator;
import com.wenzeasy.ui.presenter.LoginPresenter;
import com.wenzeasy.ui.view.LoginView;
import com.wenzeasy.util.LogCat;

import org.alfonz.utility.Logcat;

import dmax.dialog.SpotsDialog;

import static com.wenzeasy.BuildConfig.LOGCAT_TAG;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {

    private SpotsDialog dialog;
    private EditText editTextPhoneNumber;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new SpotsDialog(this, "Connexion en cours...");

        editTextPhoneNumber = findViewById(R.id.edit_phone_number);
        buttonLogin = findViewById(R.id.btn_login);
    }

    public void login(View view) {
        dialog.show();

        getPresenter().login(editTextPhoneNumber.getText().toString());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), ConfirmPinActivity.class));
            }
        },1000);

        //getPresenter().serverTest();
    }

    @NonNull
    @Override
    public LoginPresenter providePresenter() {
        return new LoginPresenter();
    }


    @Override
    public void onUnknownError(String errorMessage) {
        Toast.makeText(this, "Contact admin please!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onNetworkError() {
        Log.e(LOGCAT_TAG, "onNetworkError: ");
    }

    @Override
    public void enabledControls(boolean flag) {

    }

    @Override
    public void onFinishLogging(User user) {

    }

    @Override
    public void onServerTest(String message) {
        LogCat.d(message);
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFinish() {
        dialog.dismiss();
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this, "SMS Verification code sent!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPhoneNumberVerified() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
