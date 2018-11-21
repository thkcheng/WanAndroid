package com.lib.http.request;

import com.lib.http.CommonParams;
import com.lib.http.HttpManager;
import com.lib.http.interfaces.HttpConverter;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by thkcheng on 2018/11/21.
 */
public class PostStringRequest extends OKHttpRequest{

    @Override
    protected RequestBody buildRequestBody(CommonParams commonParams) {
        //优先使用content字段，content为空则使用params字段
        String content = commonParams.content();
        Object temp = content;
        if (content == null) {
            temp = commonParams.params();
        }
        HttpConverter httpConverter = HttpManager.getInstance().getHttpConverter();
        return httpConverter.convert(temp);
    }

    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
