package com.app.wan;


import com.starry.douban.log.BuildConfig;

public final class Settings {

    private int methodCount = 1;
    private boolean showThreadInfo = true;
    private int methodOffset = 0;

    /**
     * Determines how logs will printed
     */
    private LogLevel logLevel = LogLevel.FULL;

    public int getMethodCount() {
        return methodCount;
    }

    public boolean isShowThreadInfo() {
        return showThreadInfo;
    }

    public LogLevel getLogLevel() {
        if(!BuildConfig.DEBUG){//  No log will be printed for release environment
            logLevel = LogLevel.NONE;
        }
        return logLevel;
    }

    public int getMethodOffset() {
        return methodOffset;
    }

}
