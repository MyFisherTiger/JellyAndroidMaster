package com.kjs.fishertiger.jelly_android_master.http;

import java.util.HashMap;
import java.util.Map;


public class ApiClient {

	private static final String WECHAT_APIKEY= "128c78bcdf68be128c2a831144e4c350";

	public static Map<String, Object> getRequiredBaseParam() {
		Map<String, Object> params = new HashMap<>();
		params.put("key",WECHAT_APIKEY);
		return params;
	}
}
