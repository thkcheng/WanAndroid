package com.lib.http.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * 运行在UI线程的Handler
 *
 * Created by thkcheng on 2018/11/21.
 */
public class HttpHandler {

    private Handler mHandler;

    private HttpHandler() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    private static class Holder {
        private final static HttpHandler INSTANCE = new HttpHandler();
    }

    public static void post(Runnable runnable) {
        Holder.INSTANCE.mHandler.post(runnable);
    }

}
