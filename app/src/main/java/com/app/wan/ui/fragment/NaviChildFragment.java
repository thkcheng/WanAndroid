package com.app.wan.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.wan.Logger;
import com.app.wan.R;
import com.app.wan.base.BaseFragment;
import com.app.wan.model.WanNaviBean;
import com.app.wan.util.JsonUtil;
import com.app.wan.widget.flowlayout.FlowLayout;
import com.app.wan.widget.flowlayout.TagAdapter;
import com.app.wan.widget.flowlayout.TagFlowLayout;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by thkcheng on 2018/7/6.
 */
public class NaviChildFragment extends BaseFragment {

    @BindView(R.id.mFlowLayout)
    TagFlowLayout mFlowLayout;

    List<WanNaviBean.DataBean.ArticlesBean> datas = new ArrayList<>();

    public NaviChildFragment() {
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_navi_chil_layout;
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String jsonStr = bundle.getString("datas");
            datas = JsonUtil.toObject(jsonStr, new TypeToken<List<WanNaviBean.DataBean.ArticlesBean>>() {
            }.getType());
            Logger.i("NaviChildFragment--->>", datas.toArray().toString());
        }

        if (datas.size() == 0) return;

        final LayoutInflater mInflater = LayoutInflater.from(getActivity());
        mFlowLayout.setAdapter(new TagAdapter<WanNaviBean.DataBean.ArticlesBean>(datas) {
            @Override
            public View getView(FlowLayout parent, int position, WanNaviBean.DataBean.ArticlesBean aBean) {
                TextView tvTag = (TextView) mInflater.inflate(R.layout.item_navi_tag, mFlowLayout, false);
                tvTag.setText(aBean.getTitle());
                return tvTag;
            }
        });
    }
}
