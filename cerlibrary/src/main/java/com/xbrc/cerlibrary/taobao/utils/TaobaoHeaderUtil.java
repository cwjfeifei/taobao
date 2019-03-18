package com.xbrc.cerlibrary.taobao.utils;

import java.util.HashMap;
import java.util.Map;

public class TaobaoHeaderUtil {

    // 获取收货地址
    public static Map<String, String> getAddressHeader(String cookie) {
        Map<String, String> heades = new HashMap<String, String>();
        heades.put("Cookie", cookie);
        heades.put("Host", "h5api.m.taobao.com");
        heades.put("Referer", "http://member1.taobao.com/member/fresh/deliver_address.htm?spm=a1z09.2.a210b.8.77552e8dw14JoY");
        heades.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
        return heades;
    }

    // 获取支付宝账户
    public static Map<String, String> getAlipayHeader(String cookie) {
        Map<String, String> heades = new HashMap<String, String>();
        heades.putAll(getDefaulHeader());
        heades.put("Cache-Control", "no-cache");
        heades.put("Cookie", cookie);
        heades.put("Host", "member1.taobao.com");
        heades.put("Referer", "https://member1.taobao.com/member/fresh/certify_info.htm");
        heades.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
        return heades;
    }

    // 获取VIP等级 成长值
    public static Map<String, String> getBaseInfo5Header(String cookie) {
        Map<String, String> heades = new HashMap<String, String>();
        heades.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        heades.put("Accept-Language", "zh-CN,zh;q=0.9");
        heades.put("Cache-Control", "max-age=0");
        heades.put("Connection", "keep-alive");
        heades.put("Cookie", cookie);
        heades.put("Host", "h5.m.taobao.com");
        heades.put("Upgrade-Insecure-Requests", "1");
        heades.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
        return heades;
    }

    // 获取淘气值
    public static Map<String, String> getBaseInfo4Header(String cookie) {
        Map<String, String> heades = new HashMap<String, String>();
        heades.put("Accept", "*/*");
        heades.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        heades.put("Cache-Control", "no-cache");
        heades.put("Connection", "keep-alive");
        heades.put("Cookie", cookie);
        heades.put("Host", "vip.taobao.com");
        heades.put("Pragma", "no-cache");
        heades.put("Referer", "https://member1.taobao.com/member/fresh/certify_info.htm");
        heades.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
        return heades;
    }

    // 获取真实姓名、性别
    public static Map<String, String> getBaseInfo3Header(String cookie) {
        Map<String, String> heades = new HashMap<String, String>();
        heades.putAll(getDefaulHeader());
        heades.put("Cache-Control", "max-age=0");
        heades.put("Cookie", cookie);
        heades.put("Host", "member1.taobao.com");
        heades.put("Referer", "https://i.taobao.com/user/baseInfoSet.htm");
        return heades;
    }

    // 获取真实姓名和身份证
    public static Map<String, String> getBaseInfo2Header(String cookie) {
        Map<String, String> heades = new HashMap<String, String>();
        heades.putAll(getDefaulHeader());
        heades.put("Cache-Control", "no-cache");
        heades.put("Cookie", cookie);
        heades.put("Host", "member1.taobao.com");
        heades.put("Referer", "https://member1.taobao.com/member/fresh/account_security.htm");
        return heades;
    }

    // 获取登陆邮箱和电话号码的头部
    public static Map<String, String> getBaseInfo1Header(String cookie) {
        Map<String, String> heades = new HashMap<String, String>();
        heades.putAll(getDefaulHeader());
        heades.put("Cache-Control", "no-cache");
        heades.put("Cookie", cookie);
        heades.put("Host", "member1.taobao.com");
        heades.put("Referer", "https://login.taobao.com/member/login.jhtml");
        return heades;
    }

    // 获取淘宝id,淘宝账号,极速退款额度
    public static Map<String, String> getBaseInfoHeader(String cookie) {
        Map<String, String> heades = new HashMap<>();
        heades.putAll(getDefaulHeader());
        heades.put("Cache-Control", "max-age=0, no-cache");//差异
        heades.put("Cookie", cookie);//差异
        heades.put("Host", " i.taobao.com");//差异
        heades.put("Referer", "https://buyertrade.taobao.com/trade/itemlist/list_bought_items.htm");//差异
        return heades;
    }

    //默认的header
    public static Map<String, String> getDefaulHeader() {
        Map<String, String> heades = new HashMap<>();
        heades.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        heades.put("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        heades.put("Connection", "keep-alive");
        heades.put("Pragma", "no-cache");
        heades.put("Upgrade-Insecure-Requests", "1");
        heades.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36");
        return heades;
    }
}
