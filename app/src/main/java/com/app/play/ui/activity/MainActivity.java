package com.app.play.ui.activity;

import android.animation.ArgbEvaluator;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.play.R;
import com.app.play.base.BaseActivity;
import com.app.play.ui.adapter.MainViewPagerAdapter;
import com.app.play.ui.fragment.HomeFragment;
import com.app.play.ui.fragment.NavigationFragment;
import com.app.play.ui.fragment.ProjectFragment;
import com.app.play.ui.fragment.SystemFragment;
import com.app.play.util.viewpager.v4.FragmentPagerItem;
import com.app.play.util.viewpager.v4.FragmentPagerItemAdapter;
import com.app.play.util.viewpager.v4.FragmentPagerItems;
import com.app.play.widget.NewViewPager;

import java.util.ArrayList;
import java.util.List;

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
    RadioButton rbtnProject;

    @BindView(R.id.radioGroup_main)
    RadioGroup radioGroupMain;

    /**
     * 存放tab对象的集合
     */
    private List<RadioButton> tabViews = new ArrayList<>();
    /**
     * 存放tab对应的图片资源集合
     */
    private List<Drawable> tabDrawables = new ArrayList<>();
    /**
     * 渐变效果
     */
    private ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();
    private int colorSelect;
    private int colorUnSelect;


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
        pages.add(FragmentPagerItem.of(ProjectFragment.class.getSimpleName(), ProjectFragment.class));

        mViewPager.setOffscreenPageLimit(3);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(), pages);
        mViewPager.setAdapter(adapter);

        initTab();
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
                changeTabDrawable(position, positionOffset);
            }

            @Override
            public void onPageSelected(final int position) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setTabSelect(position);
                    }
                }, DELAY_TIME);
                tabViews.get(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    /**
     * 初始化tab
     */
    private void initTab() {
        colorSelect = ContextCompat.getColor(this, R.color.colorBlack);
        colorUnSelect = ContextCompat.getColor(this, R.color.colorGray);

        tabViews.add(rbtnHome);
        tabViews.add(rbtnSystem);
        tabViews.add(rbtnNavigation);
        tabViews.add(rbtnProject);

        tabDrawables.add(ContextCompat.getDrawable(this, R.drawable.selector_bottom_tab01).mutate());
        tabDrawables.add(ContextCompat.getDrawable(this, R.drawable.selector_bottom_tab02).mutate());
        tabDrawables.add(ContextCompat.getDrawable(this, R.drawable.selector_bottom_tab03).mutate());
        tabDrawables.add(ContextCompat.getDrawable(this, R.drawable.selector_bottom_tab04).mutate());
    }

    /**
     * 改变tab图片
     *
     * @param position       Position index of the first page currently being displayed.
     *                       Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset Value from [0, 1) indicating the offset from the page at position.
     */
    private void changeTabDrawable(int position, float positionOffset) {
        RadioButton fromTab;
        RadioButton toTab;

        Drawable drawableFrom;
        Drawable drawableTo;

        fromTab = tabViews.get(position);
        drawableFrom = tabDrawables.get(position);

        if (position != tabDrawables.size() - 1) {
            toTab = tabViews.get(position + 1);
            drawableTo = tabDrawables.get(position + 1);
        } else {
            toTab = null;
            drawableTo = null;
        }

        if (fromTab != null) {
            updateTabView(positionOffset, colorSelect, colorUnSelect, drawableFrom, fromTab);
        }
        if (toTab != null) {
            updateTabView(positionOffset, colorUnSelect, colorSelect, drawableTo, toTab);
        }
    }


    private void updateTabView(float positionOffset, int startColor, int endColor, Drawable drawable, RadioButton tab) {
        int colorStart = (int) mArgbEvaluator.evaluate(positionOffset, startColor, endColor);
        Drawable drawableColorStart = tintDrawable(drawable, ColorStateList.valueOf(colorStart));
        tab.setTextColor(colorStart);
        drawableColorStart.setBounds(0, 0, drawableColorStart.getIntrinsicWidth(), drawableColorStart.getIntrinsicHeight());
        tab.setCompoundDrawables(null, drawableColorStart, null, null);
    }

    /**
     * 修改切换tab的显示
     * @param selectIndex
     */
    private void setTabSelect(int selectIndex) {
        for (int index = 0; index < tabViews.size(); index++) {
            RadioButton imageView = tabViews.get(index);
            Drawable drawable = tabDrawables.get(index);
            int resultColor;
            if (index == selectIndex) {
                resultColor = colorSelect;
            } else {
                resultColor = colorUnSelect;
            }
            Drawable resultDrawable = tintDrawable(drawable, ColorStateList.valueOf(resultColor));
            resultDrawable.setBounds(0, 0, resultDrawable.getIntrinsicWidth(), resultDrawable.getIntrinsicHeight());
            imageView.setTextColor(resultColor);
            imageView.setCompoundDrawables(null, resultDrawable, null, null);
        }
    }

    public Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }
}
