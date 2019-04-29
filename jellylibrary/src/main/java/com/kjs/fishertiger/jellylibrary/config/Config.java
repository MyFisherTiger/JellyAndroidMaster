package com.kjs.fishertiger.jellylibrary.config;

import android.content.Context;

import io.realm.RealmMigration;

/**
 * 使用项目需要的配置
 */

public class Config {
    /**必传参数**/
    //是否为BuildConfig.DEBUG,日志输出需要
    public static boolean DEBUG;
    //设置Context
    public static Context CONTEXT;
    /**Retrofit**/
    //网络请求的域名
    public static String URL_DOMAIN;
    //网络缓存地址
    public static String URL_CACHE;
    //设置OkHttp的缓存机制的最大缓存时间,默认为一天
    public static long MAX_CACHE_SECONDS= 60 * 60 * 24;
    //缓存最大的内存,默认为10M
    public static long MAX_MEMORY_SIZE=10 * 1024 * 1024;
    //设置网络请求json通用解析类
    public static Class MClASS;
    /**SharePreference**/
    public static String USER_CONFIG;
    /**Realm**/
    public static RealmMigration realmMigration;
    public static int realmVersion=0;
    public static String realmName="myRealm.realm";

}
