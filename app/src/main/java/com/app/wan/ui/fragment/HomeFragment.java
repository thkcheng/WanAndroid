package com.app.wan.ui.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.wan.Logger;
import com.app.wan.R;
import com.app.wan.api.Apis;
import com.app.wan.base.BaseFragment;
import com.app.wan.model.WanBanner;
import com.app.wan.http.HttpManager;
import com.app.wan.http.callback.StringCallback;
import com.app.wan.http.error.ErrorModel;
import com.app.wan.model.WanHomeBean;
import com.app.wan.ui.activity.ParticularsActivity;
import com.app.wan.ui.adapter.HomeBannerAdapter;
import com.app.wan.ui.adapter.HomeRecommendAdapter;
import com.app.wan.util.ToastUtil;
import com.app.wan.widget.RecyclerViewHeader;
import com.app.wan.widget.jrecycleview.JRecyclerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class HomeFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mRefreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private HomeRecommendAdapter mAdapter;

    List<WanHomeBean.DataBean.DatasBean> recommends = new ArrayList<>();

    private int startPage = 0; //列表页码

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setNestedScrollingEnabled(false); //解决在NestedScrollView滑动粘连的问题
        mAdapter = new HomeRecommendAdapter(getActivity(), recommends);
        mRecyclerView.setAdapter(mAdapter);

        loadData();
    }

    public void setListener() {
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
    }

    @Override
    public void loadData() {
        requestBanner();
        requestRecommend();
        //主动刷新
        //mRefreshLayout.autoRefresh();
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        startPage = 0;
        requestRecommend();
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        requestRecommend();
    }

    /**
     * 请求banner数据
     */
    private void requestBanner() {
        showLoading();
        HttpManager.get()
                .tag(this)
                .url(Apis.WAN_HOME_BANNER)
                .build()
                .enqueue(new StringCallback<WanBanner>() {
                    @Override
                    public void onSuccess(WanBanner response, Object... obj) {
                        mViewPager.setAdapter(new HomeBannerAdapter(getActivity(), response.getData()));
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                    }
                });
    }

    /**
     * 请求推荐列表
     */
    private void requestRecommend() {
        HttpManager.get()
                .tag(this)
                .url(String.format(Apis.WAN_HOME_LIST, startPage))
                .acache(true)
                .build()
                .enqueue(new StringCallback<WanHomeBean>() {
                    @Override
                    public void onSuccess(WanHomeBean response, Object... obj) {
                        refreshRecommendList(response.getData());
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        ToastUtil.showToast(errorModel.getMessage());
                    }

                    @Override
                    public void onAfter(boolean success) {
                        hideLoading(success);
                        mRefreshLayout.finishRefresh();
                        mRefreshLayout.finishLoadMore();
                    }
                });
    }

    public void refreshRecommendList(WanHomeBean.DataBean response) {
        //1、如果是第一页先清空数据 books不用做非空判断，不可能为空
        if (startPage == 0) {
            recommends.clear();
        }
        //2、装载数据
        recommends.addAll(response.getDatas());
        //3、刷新适配器
        mAdapter.notifyDataSetChanged();
        //4、页码自增
        startPage++;
        //5、如果没有数据了，禁用加载更多功能
        if (response == null) mRefreshLayout.setEnableRefresh(false);
    }
}
