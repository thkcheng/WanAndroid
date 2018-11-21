package com.app.wan.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.wan.common.GsonConverter;
import com.app.wan.common.InterceptorImpl;
import com.app.wan.util.ACache;
import com.lib.http.HttpManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class BaseApp {

    private Context context;

    private ActivityCallback lifeCallback;

    private ACache aCache;

    /**** 不使用synchronized和lock 实现一个线程安全(高效)的单例 start ****/
    private BaseApp() {
    }

    public static BaseApp getInstance() {
        return BaseAppHolder.INSTANCE;
    }

    private static class BaseAppHolder {
        private final static BaseApp INSTANCE = new BaseApp();
    }
    /**** 不使用synchronized和lock 实现一个线程安全(高效)的单例 end ****/

    public static Context getContext() {
        return getInstance().context;
    }

    public void attachBaseContext(Context base) {
        context = base;
    }

    public void onCreate(Application application) {
        lifeCallback = new ActivityCallback();
        application.registerActivityLifecycleCallbacks(lifeCallback);

        aCache = ACache.get(getContext());

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                ex.printStackTrace();
                //saveCrashInfo(ex, FileUtils.getCrashDir(), Constants.FILE_CRASH_LOG);
            }
        });

        initOkHttp();
    }

    public static ACache getACache() {
        return getInstance().aCache;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        lifeCallback.finishAllActivity();
        System.exit(0);
    }

    /**
     * 获取网络是否已连接
     *
     * @return {@code true} if the network is available, {@code false} otherwise
     */
    public boolean networkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null == manager) {
            return false;
        }

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (null == networkInfo || !networkInfo.isAvailable() || !networkInfo.isConnected()) {
            return false;
        }

        return true;
    }

    private void initOkHttp() {
        HttpManager.getInstance()
                .setTimeOut(30)
                .setHttpConverter(GsonConverter.create())
                .setInterceptor(new InterceptorImpl())
                .setCertificates()
                .build();
    }
}
