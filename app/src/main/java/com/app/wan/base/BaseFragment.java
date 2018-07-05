package com.app.wan.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.wan.R;
import com.app.wan.widget.LoadingDataLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment implements IBaseUI {

    private boolean isSuccess;

    /**
     * 网络请求各种状态显示容器
     * <p>Required view 'view_loading_container' with ID 2131427348 for field 'mLoadingDataLayout' was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.
     */
    @Nullable
    @BindView(R.id.view_loading_container)
    protected LoadingDataLayout mLoadingDataLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResID(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLoadingDataLayout(view);

        initData();
        setListener();
    }

    public void setListener() {}

    /**
     * @param view
     */
    private void initLoadingDataLayout(View view) {
        mLoadingDataLayout = view.findViewById(R.id.view_loading_container);
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
     * @param networkStatus 网络请求状态
     */
    protected void showLoadingStatus(int networkStatus) {
        if (mLoadingDataLayout == null || isSuccess) return;
        mLoadingDataLayout.setStatus(networkStatus);
        if (LoadingDataLayout.STATUS_SUCCESS == networkStatus) isSuccess = true;
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
