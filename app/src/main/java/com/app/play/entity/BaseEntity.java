package com.app.play.entity;

/**
 * Created by xiexucheng on 17/2/17.
 */

public class BaseEntity<T> {

    ResultEntity result;
    T data;


    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
