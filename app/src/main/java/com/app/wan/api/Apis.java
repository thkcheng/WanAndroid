package com.app.wan.api;

/**
 * Created by thkcheng on 18/5/24.
 */
public class Apis {

    public final static String BASE_URL = "http://www.wanandroid.com/";

    /**
     * 首页文章列表
     * GET
     */
    public static String WAN_HOME_BANNER = BASE_URL + "banner/json";

    /**
     * 首页文章列表(页码从0开始)
     * GET
     */
    public static String WAN_HOME_LIST = BASE_URL + "article/list/%d/json";

    /**
     * 体系数据列表
     * GET
     */
    public static String WAN_SYSTEM_LIST = BASE_URL + "tree/json";

    /**
     * 导航数据列表
     * GET
     */
    public static String WAN_NAVI_LIST = BASE_URL + "navi/json";

    /**
     * 导航数据列表
     * GET
     */
    public static String WAN_LOGIN = BASE_URL + "user/login";

}
