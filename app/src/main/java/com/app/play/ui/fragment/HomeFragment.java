package com.app.play.ui.fragment;

import android.widget.Toast;

import com.app.play.R;
import com.app.play.api.Apis;
import com.app.play.base.BaseFragment;
import com.app.play.model.WanBannerBean;
import com.app.play.model.WanHomeBean;
import com.app.play.http.HttpManager;
import com.app.play.http.callback.StringCallback;
import com.app.play.http.error.ErrorModel;

public class HomeFragment extends BaseFragment {

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {

        HttpManager.get()
                .tag(this)
                .url(Apis.WAN_HOME_BANNER)
                .build()
                .enqueue(new StringCallback<WanBannerBean>() {
                    @Override
                    public void onSuccess(WanBannerBean response, Object... obj) {
                        
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {

                    }
                });

        /*
        HttpManager.get()
                .tag(this)
                .url(Apis.WAN_HOME_LIST)
                .build()
                .enqueue(new StringCallback<WanHomeBean>() {

                    @Override
                    public void onSuccess(WanHomeBean response, Object... obj) {
                        Toast.makeText(getActivity(), "请求成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(ErrorModel errorModel) {
                        Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
        */

    }

    @Override
    public void setListener() {
    }
}
