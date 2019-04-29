package com.kjs.fishertiger.jelly_android_master.been;


import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;

import java.util.List;

public class WeChatNewsResult {
	private int code;
	private String msg;
	private List<WeChatNews> newslist;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<WeChatNews> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<WeChatNews> newslist) {
		this.newslist = newslist;
	}
}
