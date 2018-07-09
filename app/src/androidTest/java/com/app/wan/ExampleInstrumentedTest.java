package com.app.wan;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.app.wan.http.HttpManager;
import com.app.wan.http.callback.StringCallback;
import com.app.wan.http.error.ErrorModel;
import com.app.wan.model.WanBanner;
import com.app.wan.util.TimeUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

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
}
