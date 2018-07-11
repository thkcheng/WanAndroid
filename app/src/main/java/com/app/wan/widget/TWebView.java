package com.app.wan.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.app.wan.R;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/*
    TBS_WebView介绍
    https://blog.csdn.net/niubitianping/article/details/70919385

    1. 特殊功能:

        速度快：相比系统webview的网页打开速度有30+%的提升；

        省流量：使用云端优化技术使流量节省20+%；

        更安全：安全问题可以在24小时内修复；

        更稳定：经过亿级用户的使用考验，CRASH率低于0.15%；

        兼容好：无系统内核的碎片化问题，更少的兼容性问题；

        体验优：支持夜间模式、适屏排版、字体设置等浏览增强功能；

        功能全：在Html5、ES6上有更完整支持；

        更强大：集成强大的视频播放器，支持视频格式远多于系统webview；

        视频和文件格式的支持x5内核多于系统内核

        防劫持是x5内核的一大亮点

    2. 运行环境

        手机ROM版本高于或等于2.2版本

        手机RAM大于500M，该RAM值通过手机 /proc/meminfo 文件的MemTotal动态获取

    注：如果不满足上述条件，SDK会自动切换到系统WebView，SDK使用者不用关心该切换过程。
*/

/**
 * Created by thkcheng on 2018/7/10.
 * <p>
 * 使用腾讯com.tencent.smtt.sdk.WebView
 * (http://x5.tencent.com/
 * 为防止和系统WebView混淆理应封装一层
 */
public class TWebView extends WebView {

    private ProgressView progressView;  //进度条

    private int progressHeight = 2;  //进度条的高度

    public TWebView(Context context) {
        super(context);
        initView(context);
    }

    public TWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        //创建进度条
        progressView = new ProgressView(context);
        //设置加载进度条的高度
        progressView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(context, progressHeight)));
        progressView.setProgress(10);
        progressView.setColor(context.getResources().getColor(R.color.colorAccent));
        //添加进度到WebView
        addView(progressView);

        //开启js脚本支持
        getSettings().setJavaScriptEnabled(true);
        //适配手机大小
        getSettings().setUseWideViewPort(true);
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setSupportZoom(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setDisplayZoomControls(false);


        setWebChromeClient(new TChromeClient());
        setWebViewClient(new TClient());
    }


    public class TChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView webView, int newProgress) {
            if (newProgress == 100) {
                progressView.setVisibility(GONE);
            } else {
                progressView.setProgress(newProgress);
            }
            if (mListener != null) {
                mListener.onProgressChange(webView, newProgress);
            }
            super.onProgressChanged(webView, newProgress);
        }
    }

    private class TClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //在当前Activity打开
            view.loadUrl(url);
            return true;
        }

        /*
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //https忽略证书问题
            handler.proceed();
        }
        */

        @Override
        public void onPageFinished(WebView view, String url) {
            progressView.setVisibility(GONE);
            if (mListener != null) {
                mListener.onPageFinish(view);
            }
            super.onPageFinished(view, url);
        }
    }

    private onWebViewListener mListener;

    public void setOnWebViewListener(onWebViewListener listener) {
        this.mListener = listener;
    }

    //进度回调接口
    public interface onWebViewListener {
        void onProgressChange(WebView view, int newProgress);

        void onPageFinish(WebView view);
    }

    /**
     * dp转换成px
     *
     * @param context Context
     * @param dp      dp
     * @return px值
     */
    private int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
