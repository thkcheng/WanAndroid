package com.app.play.http;

import com.app.play.entity.BaseEntity;
import com.app.play.util.HttpUtil;
import com.app.play.util.Preconditions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    public static final String TAG = BaseObserver.class.getSimpleName();

    @Override
    public void onNext(BaseEntity<T> value) {

        try {
            // 1. check value
            Preconditions.checkNotNull(value);

            // 2. check result code
            HttpUtil.checkResultCode(value.getErrorCode(), value.getErrorMsg());

            // 3. call result method
            onSuccees(value.getData());

        } catch (Exception e) {
            e.printStackTrace();
            NetworkException netEx = NetworkException.newException(e);
            onCodeError(netEx.getErrorCode(), Errors.Message.SERVER_ERROR);
        }

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }



    /**
     * 返回成功
     *
     * @param t
     */
    public abstract void onSuccees(T t);

    /**
     * 返回成功了,但是code错误
     *
     * @param errorCode
     * @param errorMsg
     */
    public abstract void onCodeError(int errorCode, String errorMsg);

    /**
     * 接口执行完成
     *
     */
    public abstract void onCompleted();

    /**
     * 接口请求失败和异常
     *
     */
    //public abstract void onFailure();
    public abstract void onFailure(boolean isNetWorkError) throws Exception;

}
