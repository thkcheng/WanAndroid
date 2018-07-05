package com.app.wan.ui.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

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
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class HomeFragment extends BaseFragment {

    ViewPager mViewPager;

    @BindView(R.id.mRecyclerView)
    XRecyclerView mRecyclerView;

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    public void initData() {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.item_home_header, null);
        mViewPager = header.findViewById(R.id.mViewPager);
        mRecyclerView.addHeaderView(header);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                request();
            }

            @Override
            public void onLoadMore() {
                request();
            }
        });
        request();
    }

    public void setListener() {
    }

    /**
     * 使用RxJava-flatMap实现先获取Banner数据再获取推荐数据
     */
    public void request() {
        //flatMap https://blog.csdn.net/yuminfeng728/article/details/77882803
        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                requestBanner(e);
            }

        }).flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
            @Override
            public ObservableSource<Boolean> apply(Boolean b) throws Exception {
                if (b) {
                    return Observable.just(true);
                }
                return Observable.just(false);
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Exception {
                requestRecommend();
            }
        });
    }

    /**
     * 请求banner数据
     */
    private void requestBanner(final ObservableEmitter<Boolean> emitter) {
        HttpManager.get()
                .tag(this)
                .url(Apis.WAN_HOME_BANNER)
                .build()
                .enqueue(new StringCallback<WanBanner>(){
                    @Override
                    public void onSuccess(WanBanner response, Object... obj) {
                        mViewPager.setAdapter(new HomeBannerAdapter(getActivity(), response.getData()));
                        emitter.onNext(true);
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
                    @Override
                    public void onAfter(boolean success) {
                        mRecyclerView.refreshComplete();
                        mRecyclerView.loadMoreComplete();
                    }
                });
    }
}
