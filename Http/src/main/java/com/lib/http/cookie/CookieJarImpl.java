package com.lib.http.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by thkcheng on 2018/11/21.
 */
public class CookieJarImpl implements CookieJar {

    private Map<String, List<Cookie>> cookieMap = new HashMap<>();

    public Map<String, List<Cookie>> getCookieMap() {
        return cookieMap;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieMap.put(url.host(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        for (Map.Entry<String, List<Cookie>> entry : cookieMap.entrySet()) {
            if (url.host().equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return new ArrayList<>();
    }
}
