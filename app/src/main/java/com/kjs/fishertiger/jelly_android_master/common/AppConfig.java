package com.kjs.fishertiger.jelly_android_master.common;

import android.os.Environment;


public class AppConfig {
    public final static String STORAGE_DIR= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Jelly/";
    public final static String URL_CACHE=STORAGE_DIR+"url/cache/";
}
