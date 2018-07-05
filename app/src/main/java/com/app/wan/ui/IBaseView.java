package com.app.wan.ui;

import com.app.wan.widget.LoadingDataLayout;

/**
 * Created by xiexucheng on 17/11/7.
 */

public interface IBaseView {

    /**
     * 显示Loading
     */
    void showLoading();

    /**
     * 隐藏Loading
     *
     * @param status 1正常 2加载失败 3数据为空 {@link LoadingDataLayout }
     */
    void hideLoading(int status);
}
