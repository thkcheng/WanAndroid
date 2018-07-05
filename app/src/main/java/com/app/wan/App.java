package com.app.wan;

import android.app.Application;
import android.content.Context;

import com.app.wan.base.BaseApp;

public class App extends Application{

    private static App mApp;
    public static App getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;
        BaseApp.getInstance().onCreate(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        BaseApp.getInstance().attachBaseContext(base);
    }

    /**
     * This method is for use in emulated process environments.  It will
     * never be called on a production Android device, where processes are
     * removed by simply killing them; no user code (including this callback)
     * is executed when doing so.
     * <p>
     * 该方法不会被回调
     * </p>
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
