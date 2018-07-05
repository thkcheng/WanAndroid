package com.app.play.http;

import com.app.play.App;
import com.app.play.Logger;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 *
 * HTTP请求管理者
 *
 * Created by thkcheng on 2018/7/4.
 */
public class HttpManager {

    public static final String TAG = "HttpManager";
    /**
     * 网络请求的缓存
     */
    private static Cache mCache;
    public static final int DEFAULT_DIR_CACHE = 10 * 1024 * 1024;
    /**
     * 超时时间
     */
    private static final int TIMEOUT = 30;
    /**
     * OkHttpClient 只有一个实例
     */
    private OkHttpClient mOkHttpClient;

    /**
     * @return HttpManager
     */
    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        /**
         * HttpManager 只有一个实例
         */
        private final static HttpManager INSTANCE = new HttpManager();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    private HttpManager() {

        File cacheFile = new File(App.getInstance().getCacheDir(), "cacheData");
        mCache = new Cache(cacheFile, DEFAULT_DIR_CACHE); //Google建议放到这里

        mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)                //连接失败后是否重新连接
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)     //从主机读取数据超时
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)        //从主机读数据超时
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)       //从主机写数据超时
                .proxy(Proxy.NO_PROXY)                         //禁止使用代理
                /*
                 * 添加通用的Header 如:token
                 */
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("token", "token-message");
                        return chain.proceed(builder.build());
                    }
                })
                /*
                 * 这里可以添加一个HttpLoggingInterceptor,因为Retrofit封装好了从Http请求到解析,
                 * 出了bug很难找出来问题,添加HttpLoggingInterceptor拦截器方便调试接口
                 */
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {

                    }
                }).setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
    }

    /**
     * Get 请求
     */
    public static CommonParams.Builder get() {
        return newBuilder(CommonParams.GET);
    }

    /**
     * Post 请求
     */
    public static CommonParams.Builder post() {
        return newBuilder(CommonParams.POST_FORM);
    }

    /**
     * Post String
     */
    public static CommonParams.Builder postString() {
        return newBuilder(CommonParams.POST_STRING);
    }

    private static CommonParams.Builder newBuilder(String method) {
        return new CommonParams.Builder(method);
    }

    /**
     * 根据tag取消请求
     *
     * @param tag 标签
     */
    public void cancelTag(Object tag) {
        OkHttpClient client = mOkHttpClient;
        for (Call call : client.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
                Logger.i("queuedCalls cancel");
            }
        }
        for (Call call : client.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
                Logger.i("runningCalls cancel");
            }
        }
    }
}
