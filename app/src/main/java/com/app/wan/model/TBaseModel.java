package com.app.wan.model;

/**
 * Created by thkcheng on 18/7/4.
 */

public class TBaseModel<T> {

    /**
     *
     *
     * "data": ...,
     * "errorCode" : 0,
     * "errorMsg": ""
     *
     *  所有的返回结构均为上述，其中errorCode如果为负数则认为错误，
     *  此时errorMsg会包含错误信息。data为Object，返回数据根据不同的接口而变化。
     *
     */

    T data;
    int errorCode;
    String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
