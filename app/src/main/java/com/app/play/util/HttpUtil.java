package com.app.play.util;

import com.app.play.http.error.Errors;
import com.app.play.http.error.NetworkException;

public class HttpUtil {

    /**
     * 检查返回结果code
     *
     * @param code    错误码
     * @param message 错误信息
     * @throws NetworkException 自定义网络异常
     */
    public static void checkResultCode(int code, String message) throws NetworkException {
        if (code != 0) {// 服务返回结果code不等于0，请求得到的数据有问题
            throw NetworkException.newException(code, message);
        }
    }


    /**
     * 检查http code
     *
     * @param code 错误码
     * @throws NetworkException 自定义网络异常
     */
    public static void checkHttpCode(int code) throws NetworkException {
        if (code < 200 || code >= 300) {// 不是2开头code统一以服务错误处理
            throw NetworkException.newException(Errors.Code.SERVER_ERROR, Errors.Message.SERVER_ERROR);
        }
    }
}
