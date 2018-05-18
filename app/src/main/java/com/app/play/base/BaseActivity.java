package com.app.play.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.play.R;
import com.app.play.ui.IBaseView;
import com.app.play.widget.LoadingDataLayout;

public abstract class BaseActivity extends AppCompatActivity implements IBaseActivity, IBaseView {

    protected LoadingDataLayout mLoadingDataLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());

        initLoadingDataLayout();

        initData();
        setListener();
    }

    private void initLoadingDataLayout() {
        mLoadingDataLayout = findViewById(R.id.view_loading_container);
        if (mLoadingDataLayout != null) {
            mLoadingDataLayout.setRetryListener(new LoadingDataLayout.OnRetryListener() {
                @Override
                public void onRetry() {
//                    loadData();
                }
            });
        }
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

    @Override
    public void showLoading() {
        showLoadingStatus(LoadingDataLayout.STATUS_LOADING);
    }

    @Override
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
