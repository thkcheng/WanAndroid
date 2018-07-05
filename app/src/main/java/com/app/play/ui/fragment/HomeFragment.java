package com.app.play.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.play.R;
import com.app.play.api.Apis;
import com.app.play.base.BaseFragment;
import com.app.play.model.WanBanner;
import com.app.play.http.HttpManager;
import com.app.play.http.callback.StringCallback;
import com.app.play.http.error.ErrorModel;
import com.app.play.model.WanHomeBean;
import com.app.play.ui.adapter.HomeBannerAdapter;
import com.app.play.ui.adapter.HomeRecommendAdapter;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        HttpManager.get()
                .tag(this)
                .url(Apis.WAN_HOME_BANNER)
                .build()
                .enqueue(new StringCallback<WanBanner>(){
                    @Override
                    public void onSuccess(WanBanner response, Object... obj) {
                        mViewPager.setAdapter(new HomeBannerAdapter(getActivity(), response.getData()));
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {

                    }
                });

        HttpManager.get()
                .tag(this)
                .url(Apis.WAN_HOME_LIST)
                .build()
                .enqueue(new StringCallback<WanHomeBean>() {

                    @Override
                    public void onSuccess(WanHomeBean response, Object... obj) {
                        mRecyclerView.setAdapter(new HomeRecommendAdapter(getActivity(), response.getData().getDatas()));
                    }
                    @Override
                    public void onFailure(ErrorModel errorModel) {
                    }
                });

    }

    @Override
    public void setListener() {
    }
}
