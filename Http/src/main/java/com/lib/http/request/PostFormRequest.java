package com.lib.http.request;

import com.lib.http.CommonParams;
import com.lib.http.callback.CommonCallback;
import com.lib.http.utils.HttpHandler;
import com.lib.http.utils.Util;

import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by thkcheng on 2018/11/21.
 */
public class PostFormRequest extends OKHttpRequest {
    @Override
    protected RequestBody buildRequestBody(CommonParams commonParams) {
        Map<String, Object> params = commonParams.params();
        List<CommonParams.FileInput> files = commonParams.files();
        if (files == null || files.isEmpty()) {
            FormBody.Builder builder = new FormBody.Builder();
            if (params != null && !params.isEmpty()) {
                for (String key : params.keySet()) {
                    String value = Util.convert(params.get(key));
                    builder.add(key, value);
                }
            }
            return builder.build();
        }else {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            if (params != null && !params.isEmpty()) {
                for (String key : params.keySet()) {
                    String value = Util.convert(params.get(key));
                    builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                            RequestBody.create(null, value));
                }
            }

            for (int i = 0; i < files.size(); i++) {
                CommonParams.FileInput fileInput = files.get(i);
                RequestBody fileBody = RequestBody.create(getMediaType(fileInput.filename), fileInput.file);
                builder.addFormDataPart(fileInput.key, fileInput.filename, fileBody);
            }
            return builder.build();
        }
    }

    private MediaType getMediaType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return MediaType.parse(contentTypeFor);
    }

    protected RequestBody wrapRequestBody(RequestBody requestBody, final CommonCallback callback) {
        return new CountingRequestBody(requestBody, new CountingRequestBody.Listener() {
            @Override
            public void onRequestProgress(final long bytesWritten, final long contentLength) {
                HttpHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.inProgress(bytesWritten * 1.0f / contentLength, contentLength);
                    }
                });
            }
        });
    }


    @Override
    protected Request buildRequest(Request.Builder builder, RequestBody requestBody) {
        return builder.post(requestBody).build();
    }
}
