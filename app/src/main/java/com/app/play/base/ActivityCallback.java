package com.app.play.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

/*
 * Application通过ActivityLifecycleCallbacks接口提供了一套回调方法，用于让开发者可以对Activity的生命周期事件进行集中处理。
 */
public class ActivityCallback implements Application.ActivityLifecycleCallbacks {

    /***
     * 寄存整个应用Activity
     **/
    private final List<Activity> activityList = new LinkedList<>();

    /**
     * finish所有的Activity（用于整个应用退出）
     */
    public void finishAll() {
        synchronized (activityList) {
            for (Activity activity : activityList) {
                activity.finish();
            }
            activityList.clear();
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityList.add(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityList.remove(activity);
    }
}
