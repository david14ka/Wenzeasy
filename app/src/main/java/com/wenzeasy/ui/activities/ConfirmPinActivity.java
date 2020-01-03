package com.wenzeasy.ui.activities;

import androidx.annotation.NonNull;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.wenzeasy.R;
import com.wenzeasy.models.User;
import com.wenzeasy.ui.presenter.LoginPresenter;
import com.wenzeasy.ui.view.LoginView;
import com.wenzeasy.util.Constants;

import dmax.dialog.SpotsDialog;

public class ConfirmPinActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView{

    public static String phoneNumber;
    private SpotsDialog dialog;
    private EditText editTextVerificationCode;
    private Button buttonConfirmCode;
    private Button buttonReSendCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pin);

        editTextVerificationCode = findViewById(R.id.editVerificationCode);
        buttonConfirmCode = findViewById(R.id.btn_confirm_code);
        buttonReSendCode = findViewById(R.id.btn_resend_code);
        dialog = new SpotsDialog(this, "Connexion en cours ....");

        buttonConfirmCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ConfirmPinActivity.this, Prefs.getString(Constants.OSMS_VERIFICATION_CODE,"zero"), Toast.LENGTH_SHORT).show();
                getPresenter().veifiyPhoneNumber(phoneNumber,editTextVerificationCode.getText().toString());
            }
        });

        buttonReSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getPresenter().resendSMS(editTextVerificationCode.getText().toString());

                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                },2000);
            }
        });

    }

    @Override
    public void onUnknownError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTimeout() {

    }

    @Override
    public void onNetworkError() {

    }

    @Override
    public void enabledControls(boolean flag) {

    }

    @Override
    public void onFinishLogging(User user) {

    }

    @Override
    public void onServerTest(String message) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onPhoneNumberVerified() {
        Toast.makeText(this, "verified successfully", Toast.LENGTH_SHORT).show();
    }

    @NonNull
    @Override
    public LoginPresenter providePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
