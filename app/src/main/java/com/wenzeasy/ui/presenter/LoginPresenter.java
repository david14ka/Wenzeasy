package com.wenzeasy.ui.presenter;

import android.util.Log;
import android.widget.Toast;

import com.wenzeasy.app.WenzeasyApplication;
import com.wenzeasy.models.User;
import com.wenzeasy.remote.client.WenzeasyClient;
import com.wenzeasy.remote.client.api.CallbackWrapper;
import com.wenzeasy.remote.client.response.LoginResponse;
import com.wenzeasy.ui.view.LoginView;

import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.rx2.RxTiPresenterDisposableHandler;

import org.alfonz.utility.Logcat;

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
        disposableHandler.manageDisposable(wenzeasyClient.login(phone, "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new CallbackWrapper<User>(getView()) {
                    @Override
                    protected void onSuccess(User user) {
                        if (isViewAttached()) {
                            getView().enabledControls(true);
                            getView().onNetworkError();
                            getView().onFinishLogging(user);

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
