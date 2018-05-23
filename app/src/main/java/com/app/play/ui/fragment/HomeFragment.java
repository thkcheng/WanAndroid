package com.app.play.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.app.play.R;
import com.app.play.base.BaseFragment;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.vBannerBg)
    View vBannerBg;

    @BindView(R.id.mMZBanner)
    MZBannerView mMZBanner;

    List<Integer> icons = new ArrayList<>();

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {

        icons.add(R.mipmap.bg_test1);
        icons.add(R.mipmap.bg_test2);
        icons.add(R.mipmap.bg_test3);
        icons.add(R.mipmap.bg_test4);
        icons.add(R.mipmap.bg_test5);

        mMZBanner.setIndicatorVisible(false);
        mMZBanner.setPages(icons, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

    }

    @Override
    public void setListener() {
    }


    public class BannerViewHolder implements MZViewHolder<Integer> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_home_banner, null);
            mImageView = view.findViewById(R.id.img_item);
            return view;
        }

        @SuppressLint("Range")
        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), icons.get(position));
            // Palette的部分
            Palette.Builder builder = Palette.from(bitmap);
            builder.generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    //获取到充满活力的这种色调
                    Palette.Swatch s1 = palette.getVibrantSwatch();       //获取到充满活力的这种色调
                    Palette.Swatch s2 = palette.getDarkVibrantSwatch();    //获取充满活力的黑
                    Palette.Swatch s3 = palette.getLightVibrantSwatch();   //获取充满活力的亮
                    Palette.Swatch s4 = palette.getMutedSwatch();           //获取柔和的色调
                    Palette.Swatch s5 = palette.getDarkMutedSwatch();      //获取柔和的黑
                    Palette.Swatch s6 = palette.getLightMutedSwatch();    //获取柔和的亮

                    //根据调色板Palette获取到图片中的颜色设置到toolbar和tab中背景，标题等，使整个UI界面颜色统一
//                    if (s1 != null) {
//                        vBannerBg.setBackgroundColor(s1.getRgb());
//                    }
//                    else if (s2 != null) {
//                        vBannerBg.setBackgroundColor(s2.getRgb());
//                    }
//                    else
                        if (s3 != null) {
                        vBannerBg.setBackgroundColor(s3.getRgb());
                    }
//                    else if (s4 != null) {
//                        vBannerBg.setBackgroundColor(s4.getRgb());
//                    }
//                    else if (s5 != null) {
//                        vBannerBg.setBackgroundColor(s5.getRgb());
//                    }
//                    else if (s6 != null) {
//                        vBannerBg.setBackgroundColor(s6.getRgb());
//                    }
                }
            });

            vBannerBg.setAlpha(200);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMZBanner.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMZBanner.pause();
    }
}
