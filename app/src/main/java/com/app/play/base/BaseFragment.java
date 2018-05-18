package com.app.play.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.play.R;
import com.app.play.ui.IBaseView;
import com.app.play.widget.LoadingDataLayout;

public abstract class BaseFragment extends Fragment implements IBaseActivity, IBaseView {

    private boolean isSuccess;

    protected LoadingDataLayout mLoadingDataLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResID(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initLoadingDataLayout(view);

        initData();
        setListener();
    }

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
}
