package com.kjs.fishertiger.jelly_android_master.common;

import android.app.Application;
import android.content.Context;

import com.kjs.fishertiger.jelly_android_master.BuildConfig;
import com.kjs.fishertiger.jelly_android_master.been.Result;
import com.kjs.fishertiger.jelly_android_master.db.CustomMigration;
import com.kjs.fishertiger.jellylibrary.config.Config;


public class AppApplication extends Application{

	private CustomMigration customMigration=new CustomMigration();

	@Override
	public void onCreate() {
		super.onCreate();
		config();
	}

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
//		MultiDex.install(this);
	}

	private void config(){
		//基本配置
		Config.DEBUG= BuildConfig.DEBUG;
		Config.CONTEXT=this;
		//Retrofit配置
		Config.URL_CACHE=AppConfig.URL_CACHE;
		Config.MClASS= Result.class;
		Config.URL_DOMAIN="http://api.tianapi.com/";
		//SharePreference配置
		Config.USER_CONFIG="Jelly_User";
		Config.realmVersion=2;
		Config.realmName="realm.realm";
		Config.realmMigration=customMigration;
	}
}
