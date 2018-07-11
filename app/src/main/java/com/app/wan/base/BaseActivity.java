package com.app.wan.base;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.wan.R;
import com.app.wan.http.HttpManager;
import com.app.wan.widget.LoadingDataLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity基类
 * <p>
 * 方法执行顺序
 * {@link #getLayoutResID()} —> {@link #initView()} —> {@link #setListener()}
 * <p>
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseUI {

    /**
     * 网络请求各种状态显示容器
     */
    @Nullable
    @BindView(R.id.view_loading_container)
    protected LoadingDataLayout mLoadingDataLayout;

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
        ButterKnife.bind(this);//必须在setContentView()之后调用

        initToolBar();
        initLoadingDataLayout();

        //保证onCreate方法第一时间执行完，显示UI界面
        //如果加载数据的方法直接在onCreate里执行，可能会导致UI界面不能及时显示
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initView();
                setListener();
            }
        });
    }

    private void initLoadingDataLayout() {
        if (mLoadingDataLayout != null) {
            mLoadingDataLayout.setRetryListener(new LoadingDataLayout.OnRetryListener() {
                @Override
                public void onRetry() {
//                    loadData();
                }
            });
        }
    }

    public void setListener() {

    }

    private void initToolBar() {
        setSupportActionBar(toolbar);
        setTitle("");//标题内容
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            Drawable drawable = getToolbarBackground();
            if (drawable != null) {
                ab.setBackgroundDrawable(drawable);
            }
            ab.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled());//显示返回键
        }
    }

    /**
     * Toolbar背景
     *
     * @return 背景
     */
    protected Drawable getToolbarBackground() {
        return null;
    }

    /**
     * 显示返回键
     *
     * @return true为显示左上角返回键，反之为false
     */
    protected boolean displayHomeAsUpEnabled() {
        return true;
    }

    /**
     * toolbar按钮点击事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//Toolbar返回键
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        //如果关闭页面，取消请求
        HttpManager.getInstance().cancelTag(this);
        super.onDestroy();
    }

    /**
     * 获取当前Activity
     *
     * @return 当前Activity
     */
    protected Activity getActivity() {
        return this;
    }

    /**
     * 展示网络请求各种状态
     *
     * @param status 网络请求状态
     */
    protected void showLoadingStatus(int status) {
        if (mLoadingDataLayout != null)
            mLoadingDataLayout.setStatus(status);
    }

    public void showLoading() {
        showLoadingStatus(LoadingDataLayout.STATUS_LOADING);
    }

    public void hideLoading(int status) {
        switch (status) {
            case LoadingDataLayout.STATUS_SUCCESS:
                showLoadingStatus(LoadingDataLayout.STATUS_SUCCESS);
                break;

            case LoadingDataLayout.STATUS_ERROR:
                showLoadingStatus(LoadingDataLayout.STATUS_ERROR);
                break;
        }
    }

}
