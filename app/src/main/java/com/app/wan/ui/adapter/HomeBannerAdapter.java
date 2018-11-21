package com.app.wan.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.wan.manager.ImageManager;
import com.app.wan.model.WanBanner;
import com.app.wan.ui.activity.ParticularsActivity;

import java.util.List;

/**
 * Created by thkcheng on 2018/7/5.
 */

public class HomeBannerAdapter extends PagerAdapter {

    Context mContext;
    private ImageView imageView;
    private List<WanBanner.WanBannerBean> bannerList;

    public HomeBannerAdapter(Context mContext, List<WanBanner.WanBannerBean> bannerList) {
        this.mContext = mContext;
        this.bannerList = bannerList;
    }


    @Override
    public int getCount() {
        return bannerList == null ? 0 : Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {

        imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageManager.loadImage(mContext, imageView, bannerList.get(position % bannerList.size()).getImagePath());
        container.addView(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ParticularsActivity.class);
                intent.putExtra("link", bannerList.get(position).getUrl());
                mContext.startActivity(intent);
            }
        });
        return imageView;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
