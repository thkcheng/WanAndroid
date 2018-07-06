package com.app.wan.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.app.wan.R;
import com.app.wan.model.WanNaviBean;
import com.app.wan.ui.fragment.NaviChildFragment;
import com.app.wan.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;

/**
 * Created by thkcheng on 2018/7/6.
 */

public class NaviViewPagerAdapter extends FragmentPagerAdapter implements TabAdapter{

    Context mContext = null;
    List<WanNaviBean.DataBean> datas = new ArrayList<>();

    public NaviViewPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    public void loadData(List<WanNaviBean.DataBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Fragment getItem(int position) {
        return getFragment(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }

    /*** 以下为tab绑定数据 **/

    @Override
    public ITabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public ITabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public ITabView.TabTitle getTitle(int position) {
        return new ITabView.TabTitle.Builder()
                .setContent(datas.get(position).getName())
                .setTextColor(mContext.getResources().getColor(R.color.colorBlue), mContext.getResources().getColor(R.color.colorBlack))
                .build();
    }

    @Override
    public int getBackground(int position) {
        return 0;
    }


    private Fragment getFragment(int position) {
        NaviChildFragment fragment = new NaviChildFragment();
        Bundle args = new Bundle();
        args.putString("datas", JsonUtil.toJson(datas.get(position).getArticles()));
        fragment.setArguments(args);
        return fragment;
    }
}
