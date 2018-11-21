package com.lib.http.callback;

import com.lib.http.HttpManager;
import com.lib.http.interfaces.HttpConverter;
import com.lib.http.utils.Util;

import okhttp3.Response;

/**
 * Created by thkcheng on 2018/11/21.
 */
public abstract class StringCallback<T> extends CommonCallback<T> {

    @Override
    public T parseResponse(Response response) throws Exception {
        HttpConverter httpConverter = HttpManager.getInstance().getHttpConverter();
        T result = httpConverter.convert(getClass(), response.body());
        Util.checkNotNull(result);
        return result;
    }
}
