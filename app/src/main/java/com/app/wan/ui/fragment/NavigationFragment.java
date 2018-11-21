package com.app.wan.ui.fragment;

import android.support.v4.view.ViewPager;

import com.app.wan.R;
import com.app.wan.api.Apis;
import com.app.wan.base.BaseFragment;
import com.app.wan.model.WanNaviBean;
import com.app.wan.ui.adapter.NaviViewPagerAdapter;
import com.lib.http.HttpManager;
import com.lib.http.callback.StringCallback;
import com.lib.http.error.ErrorModel;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class NavigationFragment extends BaseFragment {

    @BindView(R.id.mTablayout)
    VerticalTabLayout mTablayout;

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    NaviViewPagerAdapter naviAdapter = null;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_navigation;
    }

    @Override
    public void initView() {
        naviAdapter = new NaviViewPagerAdapter(getChildFragmentManager(), getActivity());
        mViewPager.setAdapter(naviAdapter);
        mTablayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void setListener() {
    }

    @Override
    public void loadData() {
        HttpManager.get(Apis.WAN_NAVI_LIST)
                .tag(this)
                .build()
                .enqueue(new StringCallback<WanNaviBean>() {
                    @Override
                    public void onSuccess(WanNaviBean response, Object... obj) {
                        naviAdapter.loadData(response.getData());
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                    }
                });
    }

    @Override
    public void onLazyLoadingData() {
        super.onLazyLoadingData();
        loadData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
