package com.xbrc.cerlibrary.taobao.utils;

import com.google.gson.Gson;

import java.util.Map;

public class GsonUtil {

    public static <T> T changeJsonToBean(String jsonString, Class<T> cls) {
        Gson gson = new Gson();
        T t = gson.fromJson(jsonString, cls);
        return t;
    }


    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }
}
