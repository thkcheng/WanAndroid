package com.app.wan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.wan.R;
import com.app.wan.widget.LoadingDataLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements IBaseUI {

    /**
     * 网络请求各种状态显示容器
     */
    @Nullable
    @BindView(R.id.view_loading_container)
    protected LoadingDataLayout mLoadingDataLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResID());
        ButterKnife.bind(this);//必须在setContentView()之后调用

        initLoadingDataLayout();

        //保证onCreate方法第一时间执行完，显示UI界面
        //如果加载数据的方法直接在onCreate里执行，可能会导致UI界面不能及时显示
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initData();
                setListener();
            }
        });
    }

    public void setListener() {}

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
