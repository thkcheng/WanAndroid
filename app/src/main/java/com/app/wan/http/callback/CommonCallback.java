package com.app.wan.http.callback;

import com.app.wan.Logger;
import com.app.wan.http.error.ErrorModel;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * 网络请求回调类
 * @param <T> 解析的对象
 *
 * Created by thkcheng on 2018/7/4.
 */
public abstract class CommonCallback<T> {

    public final static CommonCallback<String> NO_CALLBACK = new StringCallback<String>() {
        @Override
        public void onSuccess(String response, Object... obj) {

        }

        @Override
        public void onFailure(ErrorModel errorModel) {

        }
    };

    /**
     * 开始执行网络请求
     */
    public void onBefore() {
    }

    /**
     * parse {@link Response}
     *
     * @param response {@link Response}
     * @throws Exception
     */
    public abstract T parseResponse(Response response) throws Exception;

    /**
     * @param response 返回的对象
     * @param obj      可扩展参数
     */
    public abstract void onSuccess(T response, Object... obj);

    /**
     * @param errorModel 错误信息
     */
    public abstract void onFailure(ErrorModel errorModel);

    /**
     * 网络请求结束
     *
     * @param success 请求状态标记，true表示请求成功结果正确
     */
    public void onAfter(boolean success) {
    }

    /**
     * @param progress 进度
     * @param total    总长度
     */
    public void inProgress(float progress, long total) {
        Logger.i("progress=%f total=%d", progress, total);
    }

    /**
     * 获取Json对象的类型，因为数据可能是Json数组也可能是Json对象
     */
    public Type getType() {
        Type type = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (type instanceof Class) {//如果是Object直接返回
            return type;
        } else {//如果是集合，获取集合的类型map或list
            return new TypeToken<T>() {
            }.getType();
        }
    }

}
