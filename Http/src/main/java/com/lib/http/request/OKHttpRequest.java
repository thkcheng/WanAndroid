package com.lib.http.request;

import com.lib.http.CommonParams;
import com.lib.http.callback.CommonCallback;

import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by thkcheng on 2018/11/21.
 */
public abstract class OKHttpRequest {

    protected String buildUrl(CommonParams commonParams) {
        return commonParams.url();
    }

    protected abstract RequestBody buildRequestBody(CommonParams commonParams);

    protected abstract Request buildRequest(Request.Builder builder, RequestBody requestBody);

    protected RequestBody wrapRequestBody(RequestBody requestBody, CommonCallback callback) {
        return requestBody;
    }

    private Headers buildHeaders(CommonParams commonParams) {
        Map<String, String> headers = commonParams.headers();
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers != null && !headers.isEmpty()) {
            for (String key : headers.keySet()) {
                headerBuilder.add(key, headers.get(key));
            }
        }
        return headerBuilder.build();
    }

    public Request build(CommonParams commonParams, CommonCallback callback) {
        Request.Builder requestBuilder = new Request.Builder()
                .tag(commonParams.tag())
                .url(buildUrl(commonParams))
                .headers(buildHeaders(commonParams));
        RequestBody requestBody = wrapRequestBody(buildRequestBody(commonParams), callback);
        return buildRequest(requestBuilder, requestBody);
    }
}
