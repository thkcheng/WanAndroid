package com.app.wan.ui.activity;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.wan.R;
import com.app.wan.base.BaseActivity;
import com.app.wan.ui.fragment.HomeFragment;
import com.app.wan.ui.fragment.NavigationFragment;
import com.app.wan.ui.fragment.SettingFragment;
import com.app.wan.ui.fragment.SystemFragment;
import com.app.wan.util.ArgbEvaluatorUtil;
import com.app.wan.util.viewpager.v4.FragmentPagerItem;
import com.app.wan.util.viewpager.v4.FragmentPagerItemAdapter;
import com.app.wan.util.viewpager.v4.FragmentPagerItems;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @BindView(R.id.bottomTab1)
    RadioButton rbtnHome;

    @BindView(R.id.bottomTab2)
    RadioButton rbtnSystem;

    @BindView(R.id.bottomTab3)
    RadioButton rbtnNavigation;

    @BindView(R.id.bottomTab4)
    RadioButton rbtnSetting;

    @BindView(R.id.radioGroup_main)
    RadioGroup radioGroupMain;

    private ArgbEvaluatorUtil argbEvaluatorUtil = ArgbEvaluatorUtil.get();

    @Override
    public int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of(HomeFragment.class.getSimpleName(), HomeFragment.class));
        pages.add(FragmentPagerItem.of(SystemFragment.class.getSimpleName(), SystemFragment.class));
        pages.add(FragmentPagerItem.of(NavigationFragment.class.getSimpleName(), NavigationFragment.class));
        pages.add(FragmentPagerItem.of(SettingFragment.class.getSimpleName(), SettingFragment.class));

        mViewPager.setOffscreenPageLimit(4);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        mViewPager.setAdapter(adapter);

        argbEvaluatorUtil.addTab(rbtnHome, rbtnSystem, rbtnNavigation, rbtnSetting);
        argbEvaluatorUtil.addTabDrawable(R.mipmap.icon_home, R.mipmap.icon_tixi, R.mipmap.icon_navi, R.mipmap.icon_setting);
    }


    @Override
    public void setListener() {
        radioGroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bottomTab1:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.bottomTab2:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.bottomTab3:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.bottomTab4:
                        mViewPager.setCurrentItem(3, false);
                        break;
                }
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private final int DELAY_TIME = 100;
            private Handler handler = new Handler();

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                argbEvaluatorUtil.changeTabDrawable(position, positionOffset);
            }

            @Override
            public void onPageSelected(final int position) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        argbEvaluatorUtil.setTabSelect(position);
                    }
                }, DELAY_TIME);
                argbEvaluatorUtil.setChecked(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
