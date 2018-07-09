package com.app.wan.ui.fragment;

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
import com.app.wan.ui.adapter.HomeBannerAdapter;
import com.app.wan.ui.adapter.HomeRecommendAdapter;
import com.app.wan.widget.RecyclerViewHeader;
import com.app.wan.widget.jrecycleview.JRecyclerView;

import java.util.LinkedHashMap;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private int start = 0;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
    }

    public void setListener() {

    }

    @Override
    public void loadData() {
        /**
         * 使用RxJava-flatMap实现先获取Banner数据再获取推荐数据
         */
        Observable.create(new ObservableOnSubscribe<WanBanner>() {
            @Override
            public void subscribe(ObservableEmitter<WanBanner> e) throws Exception {
                requestBanner(e);
                Logger.i("HomeFragment", "subscribe====" + Thread.currentThread().getId());
            }

        })
//        .subscribeOn(Schedulers.io())
//        .observeOn(Schedulers.io())
                .flatMap(new Function<WanBanner, ObservableSource<WanBanner>>() {
                    @Override
                    public ObservableSource<WanBanner> apply(WanBanner bean) throws Exception {
                        if (bean != null) {
                            return Observable.just(bean);
                        }
                        return Observable.just(null);
                    }
                })
                .subscribe(new Consumer<WanBanner>() {
                    @Override
                    public void accept(WanBanner bean) throws Exception {
                        if (bean != null) {
                            requestRecommend();
                            Logger.i("HomeFragment", "accept====" + Thread.currentThread().getId());
                        }
                    }
                });
    }

    /**
     * 请求banner数据
     */
    private void requestBanner(final ObservableEmitter<WanBanner> emitter) {
        showLoading();
        HttpManager.get()
                .tag(this)
                .url(Apis.WAN_HOME_BANNER)
                .build()
                .enqueue(new StringCallback<WanBanner>(){
                    @Override
                    public void onSuccess(WanBanner response, Object... obj) {
                        mViewPager.setAdapter(new HomeBannerAdapter(getActivity(), response.getData()));
                        emitter.onNext(response);
                        Logger.i("HomeFragment", "requestBanner====" + Thread.currentThread().getId());
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
                .url(String.format(Apis.WAN_HOME_LIST,start++))
                .build()
                .enqueue(new StringCallback<WanHomeBean>() {
                    @Override
                    public void onSuccess(WanHomeBean response, Object... obj) {
                        mRecyclerView.setAdapter(new HomeRecommendAdapter(getActivity(), response.getData().getDatas()));
                        Logger.i("HomeFragment", "requestRecommend====" + Thread.currentThread().getId());
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
}
