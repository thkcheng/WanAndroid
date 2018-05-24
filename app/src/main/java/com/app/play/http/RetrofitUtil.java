package com.app.play.http;

import com.app.play.App;
import com.app.play.api.ApiServer;
import com.app.play.api.URLs;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by thkcheng on 18/5/24.
 */
public class RetrofitUtil {

    private static Cache mCache;

    public static final int DEFAULT_DIR_CACHE = 10 * 1024 * 1024;

    private static final long TIMEOUT = 30;

    public static volatile ApiServer apiServer = null;

    private RetrofitUtil() {}

    public static ApiServer getInstance() {

        if (apiServer == null) {
            synchronized (RetrofitUtil.class) { //双重加锁机制,后面也要对空判断,假设两个线程都到这里,不判断,下一个线程还是会再创建
                if (apiServer == null) {
                    apiServer = getApiServer();
                }
            }
        }

        return apiServer;
    }

    public static ApiServer getApiServer() {

        File cacheFile = new File(App.getInstance().getCacheDir(), "cacheData");

        mCache = new Cache(cacheFile, DEFAULT_DIR_CACHE); //Google建议放到这里

        OkHttpClient client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)                //连接失败后是否重新连接
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)     //从主机读取数据超时
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)        //从主机读数据超时
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)       //从主机写数据超时
                /*
                 * 添加通用的Header 如:token
                 */
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request.Builder builder = chain.request().newBuilder();
                        builder.addHeader("token", "taken-message");
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
                .cache(mCache) //添加缓存
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.BASE_URL) //baseURL提倡以“/”结尾
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())) //使用Gosn解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())    //适配工厂
                .build();

        return retrofit.create(ApiServer.class);
    }

}
