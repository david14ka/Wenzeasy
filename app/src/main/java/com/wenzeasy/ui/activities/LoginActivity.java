package com.wenzeasy.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.wenzeasy.R;
import com.wenzeasy.models.User;
import com.wenzeasy.remote.client.api.OSmsApi;
import com.wenzeasy.ui.presenter.LoginPresenter;
import com.wenzeasy.ui.view.LoginView;
import com.wenzeasy.util.LogCat;

import org.alfonz.utility.Logcat;

import dmax.dialog.SpotsDialog;

import static com.wenzeasy.BuildConfig.LOGCAT_TAG;

public class LoginActivity extends BaseActivity<LoginPresenter, LoginView> implements LoginView {
    private SpotsDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new SpotsDialog(this, "Connexion en cours...");
    }

    public void login(View view) {
        dialog.show();

        OSmsApi.sendMessage("+243895026521", new OSmsApi.OnSmsAuthListener() {
            @Override
            public void finish(String s) {
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                Log.d(LOGCAT_TAG, "finish: "+s);


                dialog.dismiss();
            }

            @Override
            public void failure(String message, Throwable t) {
                dialog.dismiss();

                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
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
        Toast.makeText(this, "onNetworkError", Toast.LENGTH_SHORT).show();
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
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}
