package com.app.wan.base;

/**
 * Activity,Fragment
 *
 * Created by thkcheng on 2018/7/5.
 */

public interface IBaseUI {

    /**
     * 获取视图
     */
    int getLayoutResID();

    /**
     * 初始化数据 在{@link #getLayoutResID()} 之后
     */
    void initView();

}
