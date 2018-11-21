package com.app.wan.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.wan.R;
import com.app.wan.api.Apis;
import com.app.wan.base.BaseFragment;
import com.app.wan.model.WanSystemBean;
import com.app.wan.ui.adapter.SystemListAdapter;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;

import butterknife.BindView;

public class SystemFragment extends BaseFragment {

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_system;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void setListener() {
    }

    @Override
    public void loadData() {
        HttpManager.get(Apis.WAN_SYSTEM_LIST)
                .tag(this)
                .build()
                .enqueue(new StringCallback<WanSystemBean>() {
                    @Override
                    public void onSuccess(WanSystemBean response, Object... obj) {
                        mRecyclerView.setAdapter(new SystemListAdapter(getActivity(),response.getData()));
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                    }

                    @Override
                    public void onAfter(boolean success) {
                        hideLoading(success);
                    }
                });
    }

    @Override
    public void onLazyLoadingData() {
        super.onLazyLoadingData();
        loadData();
    }
}
