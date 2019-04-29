package com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache;

import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jellylibrary.mvp.BasePresenter;
import com.kjs.fishertiger.jellylibrary.mvp.BaseView;

import java.util.List;



public interface WeChatFeaturedContract {

	interface View extends BaseView {
		void refreshUI(List<WeChatNews> newsList);
	}

	abstract class Presenter extends BasePresenter<View> {
		public abstract void requestFeaturedNews(int page, int num);
	}
}
