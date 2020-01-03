package com.wenzeasy.ui.view;

import com.wenzeasy.models.User;

import net.grandcentrix.thirtyinch.callonmainthread.CallOnMainThread;

public interface LoginView extends BaseView {

    @CallOnMainThread
    void onUnknownError(String errorMessage);

    @CallOnMainThread
    void onTimeout();

    @CallOnMainThread
    void onNetworkError();

    @CallOnMainThread
    void enabledControls(boolean flag);

    @CallOnMainThread
    void onFinishLogging(User user);

    @CallOnMainThread
    void onServerTest(String message);

    @CallOnMainThread
    void onFinish();

    @CallOnMainThread
    void onLoginSuccess();

    @CallOnMainThread
    void onPhoneNumberVerified();
}
