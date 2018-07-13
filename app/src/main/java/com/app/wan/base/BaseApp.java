package com.app.wan.base;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.app.wan.util.ACache;

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
    }

    public static ACache getACache() {
        return getInstance().aCache;
    }

    /**
     * 退出应用
     */
    public void exitApp() {
        lifeCallback.finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());// 杀死进程
        android.os.Process.killProcess(android.os.Process.myTid());// 杀死当前UI线程
        android.os.Process.killProcess(android.os.Process.myUid());// 杀死当前用户
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

    /**
     * 保存崩溃信息
     */
    private void saveCrashInfo(Throwable ex, File crashDir, String crashName) {
        StringBuilder sb = new StringBuilder();
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        sb.append(info.toString());
        printWriter.close();
        File crashFile = new File(crashDir, crashName);
        FileWriter writer = null;
        try {
            writer = new FileWriter(crashFile, true);
            writer.write(sb.toString());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
