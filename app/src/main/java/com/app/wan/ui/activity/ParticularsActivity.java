package com.app.wan.ui.activity;

import android.graphics.PixelFormat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.app.wan.R;
import com.app.wan.base.BaseActivity;
import com.app.wan.widget.TWebView;

import butterknife.BindView;

/**
 * Created by thkcheng on 2018/7/10.
 * <p>
 * 使用腾讯com.tencent.smtt.sdk.WebView
 * (http://x5.tencent.com/
 * 为防止和系统WebView混淆理应封装一层
 */
public class ParticularsActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tWebView)
    TWebView tWebView;

    @Override
    public int getLayoutResID() {
        return R.layout.activity_particulars_layout;
    }

    @Override
    public void initView() {
        setTitle("内容详情");
        //建议声明
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        //避免输入法界面弹出后遮挡输入光标的问题
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        tWebView.loadUrl(getIntent().getStringExtra("link"));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && tWebView.canGoBack()) {
            tWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
