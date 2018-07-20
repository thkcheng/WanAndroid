package com.app.wan;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.app.wan.http.HttpManager;
import com.app.wan.http.callback.StringCallback;
import com.app.wan.http.error.ErrorModel;
import com.app.wan.model.WanBanner;
import com.app.wan.util.StringUtil;
import com.app.wan.util.TimeUtil;
import com.app.wan.util.ToastUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private String TAG = getClass().getSimpleName();

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.app.play", appContext.getPackageName());
    }

    @Test
    public void Http() throws Exception {

        final String url = "http://www.wanandroid.com/banner/json";
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                Logger.e("XXX", "http start");
                WanBanner wanBanner = HttpManager.get()
                        .tag(this)
                        .url(url)
                        .build()
                        .execute(new StringCallback<WanBanner>() {
                            @Override
                            public void onSuccess(WanBanner response, Object... obj) {
                                Logger.e("XXX", "onSuccess");
                            }

                            @Override
                            public void onFailure(ErrorModel errorModel) {
                                Logger.e("XXX", "onFailure");
                            }
                        });
                Logger.e("XXX", wanBanner.getData().get(0).getTitle());
                Logger.e("XXX", "http end");
                Logger.e("XXX", TAG);
            }
        });

    }

    @Test
    public void hashCodeTest() {

        String url = "http://api.228.cn/system/validate/My41LjQ=?access_phone_type=android&app_version=My41LjQ=&nc=60";

        Map<String, String> map1 = new HashMap<>();
        map1.put("tag", "tag");
        map1.put("orderid", "12345");
        map1.put("type", "2");
        map1.put("date", "2018-7-16");
        map1.put("ap", "0");
        map1.put("phone", "18410177727");
        Logger.e("==================>>>>>    " + urlPage(url));
        Logger.e("==================>>>>>    " + TruncateUrlPage(url));
        Logger.e("==================>>>>>    " + URLRequest(url));
        Logger.e("==================>>>>>    " + URLRequest(url).hashCode());
        Logger.e("==================>>>>>    " + StringUtil.URLRequest(url));
    }

    /**
     * 解析出url请求的路径，包括页面
     * @param url url地址
     * @return url路径
     */
    public String urlPage(String url) {

        String strPage = null;
        String[] arrSplit = null;

        url = url.trim().toLowerCase();

        arrSplit = url.split("[?]");
        if (url.length() > 0) {
            if (arrSplit.length > 1) {
                if (arrSplit[0] != null) {
                    strPage = arrSplit[0];
                }
            }
        }
        return strPage;
    }

    /**
     * 去掉url中的路径，留下请求参数部分
     * @param strURL url地址
     * @return url请求参数部分
     */
    private static String TruncateUrlPage(String strURL)
    {
        String strAllParam=null;
        String[] arrSplit=null;

        strURL=strURL.trim().toLowerCase();

        arrSplit=strURL.split("[?]");
        if(strURL.length()>1)
        {
            if(arrSplit.length>1)
            {
                if(arrSplit[1]!=null)
                {
                    strAllParam=arrSplit[1];
                }
            }
        }

        return strAllParam;
    }

    /**
     * 解析出url参数中的键值对
     * 如 "index.jsp?Action=del&id=123"，解析出Action:del,id:123存入map中
     * @param URL  url地址
     * @return  url请求参数部分
     */
    public static Map<String, String> URLRequest(String URL)
    {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit=null;

        String strUrlParam=TruncateUrlPage(URL);
        if(strUrlParam==null)
        {
            return mapRequest;
        }
        //每个键值为一组 www.2cto.com
        arrSplit=strUrlParam.split("[&]");
        for(String strSplit:arrSplit)
        {
            String[] arrSplitEqual=null;
            arrSplitEqual= strSplit.split("[=]");

            //解析出键值
            if(arrSplitEqual.length>1)
            {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            }
            else
            {
                if(arrSplitEqual[0]!="")
                {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }

}
