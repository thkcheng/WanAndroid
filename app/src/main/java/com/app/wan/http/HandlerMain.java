package com.app.wan.http;

import android.os.Handler;
import android.os.Looper;

/**
 * 运行在UI线程的Handler
 *
 * Created by thkcheng on 2018/6/1.
 */
public class HandlerMain {

    private Handler mHandler;

    private HandlerMain() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    public static HandlerMain getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private final static HandlerMain INSTANCE = new HandlerMain();
    }

    public static Handler getHandler() {
        return getInstance().mHandler;
    }
}
