package com.app.wan.http.callback;

import com.app.wan.Logger;
import com.app.wan.base.BaseApp;
import com.app.wan.common.Constants;
import com.app.wan.http.CommonParams;
import com.app.wan.http.HttpManager;
import com.app.wan.http.error.NetworkException;
import com.app.wan.util.ACache;
import com.app.wan.util.JsonUtil;
import com.app.wan.util.Preconditions;

import org.json.JSONObject;

import okhttp3.Response;

/**
 * Created by thkcheng on 2018/7/4.
 */

public abstract class StringCallback<T> extends CommonCallback<T>{

    private final String TAG = HttpManager.TAG;

    private String code = "code";

    private String msg = "msg";

    @Override
    public T parseResponse(Response response) throws Exception {
        // 1. print json log
        String json = response.body().string();
        logResponse(response.request().url().toString(), json);
        response.close(); //To avoid leaking resources

        // 2. check result code
        JSONObject jsonObject = new JSONObject(json);
        int responseCode = jsonObject.optInt(code);
        String responseMsg = jsonObject.optString(msg);
        checkResultCode(responseCode, responseMsg);

        // 3. parse json to object
        T result = JsonUtil.toObject(json, getType());
        Preconditions.checkNotNull(result);

        // 4. add cache
        cacheResponse(json, commonParams);
        return result;
    }

    @Override
    public T parseCacheJson(String cacheJson) throws Exception {

        // 1. get cache
        ACache aCache = BaseApp.getACache();
        String json = aCache.getAsString(commonParams.url());

        // 2. parse json to object
        T result = JsonUtil.toObject(json, getType());
        Preconditions.checkNotNull(result);
        return result;
    }

    /**
     * 打印返回报文
     *
     * @param url  URL
     * @param json 返回报文
     */
    private void logResponse(String url, String json) {
        // 日志格式
        // Response
        // --> http://www.wanandroid.com/article/list/0/json?tag=tag
        // --> {"data":{"curPage":1,"datas":[{"apkLink":"","author":"zfman","chapterId":……
        String result = String.format("Response\n >>> %s\n >>> %s", url, json);
        Logger.i(TAG, result);
    }

    /**
     * 检查返回结果code
     *
     * @param code    错误码
     * @param message 错误信息
     * @throws NetworkException 自定义网络异常
     */
    private void checkResultCode(int code, String message) throws NetworkException {
        if (code != 0) {// 服务返回结果code不等于0，请求得到的数据有问题
            throw NetworkException.newException(code, message);
        }
    }


    /**
     * 根据commonParams.acache缓存数据
     *
     * @param json
     * @param commonParams
     */
    private void cacheResponse(String json, CommonParams commonParams) {
        if (commonParams.acache()) {
            ACache aCache = BaseApp.getACache();
            aCache.put(commonParams.url(), json, commonParams.time()); //缓存有效时间默认10秒
        }
    }

}
