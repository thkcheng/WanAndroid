package com.app.wan.ui.activity;

import android.content.Intent;

import com.app.wan.R;
import com.app.wan.base.BaseActivity;
import com.app.wan.widget.SuperTextView;

import butterknife.BindView;

/**
 * Created by thkcheng on 2018/7/11.
 */
public class AppStartActivity extends BaseActivity implements SuperTextView.OnDynamicListener {

    @BindView(R.id.superTV)
    SuperTextView superTV;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_app_start_layout;
    }

    @Override
    public void initView() {

        /*
        //设置动画的动态监听（动画结束后回调）
        superTV.setOnDynamicListener(this);
        //设置动画的动态风格
        superTV.setDynamicStyle(SuperTextView.DynamicStyle.TYPEWRITING);
        //设置动画的动态文本
        superTV.setDynamicText("WanAndroid");
        //开始动画
        superTV.start();
        */
        startActivity(new Intent(AppStartActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onChange(int position) {

    }

    @Override
    public void onCompile() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(AppStartActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}
