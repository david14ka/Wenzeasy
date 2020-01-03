package com.wenzeasy.ui.presenter;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.pixplicity.easyprefs.library.Prefs;
import com.wenzeasy.app.WenzeasyApplication;
import com.wenzeasy.models.User;
import com.wenzeasy.remote.client.WenzeasyClient;
import com.wenzeasy.remote.client.api.CallbackWrapper;
import com.wenzeasy.remote.client.api.OSmsApi;
import com.wenzeasy.remote.client.api.ServiceGenerator;
import com.wenzeasy.remote.client.response.LoginResponse;
import com.wenzeasy.ui.activities.ConfirmPinActivity;
import com.wenzeasy.ui.activities.LoginActivity;
import com.wenzeasy.ui.view.LoginView;
import com.wenzeasy.util.Constants;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx2.RxTiPresenterDisposableHandler;

import org.alfonz.utility.Logcat;

import java.util.Random;
import java.util.concurrent.locks.Lock;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import static com.wenzeasy.BuildConfig.LOGCAT_TAG;


public class LoginPresenter extends TiPresenter<LoginView> {

    private RxTiPresenterDisposableHandler disposableHandler = new RxTiPresenterDisposableHandler(this);
    private WenzeasyClient wenzeasyClient;

    @Override
    protected void onCreate() {
        super.onCreate();

        wenzeasyClient = WenzeasyApplication.getWenzeasyContext().getWenzeasyClient();
    }

    public void login(String phone) {

        String verificationCodeNumber = String.valueOf(new Random().nextInt(99999));
        String smsMessage = "Your Wenzeasy verification phone number is: \nOPT- " + verificationCodeNumber;

        Prefs.putString(Constants.OSMS_VERIFICATION_CODE,verificationCodeNumber);

        OSmsApi.sendMessage(phone,smsMessage, new OSmsApi.OnSmsAuthListener() {
            @Override
            public void finish(String s) {
                Log.d(LOGCAT_TAG, "finish: "+s);

                getView().onLoginSuccess();

            }

            @Override
            public void failure(String message, Throwable t) {
            }
        });

    }

    public void resendSMS(String phone){
        login(phone);
    }

    public void veifiyPhoneNumber(String phoneNumber, String enteredCode){

        String verficationCodeSent = Prefs.getString(Constants.OSMS_VERIFICATION_CODE,"0000");
        if (verficationCodeSent.equalsIgnoreCase(enteredCode)){
            getView().onPhoneNumberVerified();
        }else {
            getView().onUnknownError("Code incorrect, try again later");
        }
    }

    public void login2(String phone){
        disposableHandler.manageDisposable(wenzeasyClient.login(phone, "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new CallbackWrapper<User>(getView()) {
                    @Override
                    protected void onSuccess(User user) {
                        if (isViewAttached()) {
                            getView().enabledControls(true);
                            //getView().onNetworkError();
                            //getView().onFinishLogging(user);

                        }
                    }
                }));
    }

    public void serverTest() {
        disposableHandler.manageDisposable(wenzeasyClient.serverTest()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new CallbackWrapper<String>(getView()) {
                    @Override
                    protected void onSuccess(String s) {
                        Log.d(LOGCAT_TAG, "onSuccess: "+s);
                        getView().onServerTest(s);
                        getView().onFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e(LOGCAT_TAG, "onSuccess: ",e);
                        getView().onNetworkError();
                        getView().onFinish();
                    }
                }));
    }

}
