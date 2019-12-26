package com.wenzeasy.ui.activities;

import android.content.Context;

import net.grandcentrix.thirtyinch.TiActivity;
import net.grandcentrix.thirtyinch.TiPresenter;
import net.grandcentrix.thirtyinch.TiView;

public abstract class BaseActivity<P extends TiPresenter<V>, V extends TiView> extends TiActivity<P, V>{

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }
}