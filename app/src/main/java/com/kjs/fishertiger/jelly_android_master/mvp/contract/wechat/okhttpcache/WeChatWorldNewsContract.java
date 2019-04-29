package com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache;


import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jellylibrary.mvp.BasePresenter;
import com.kjs.fishertiger.jellylibrary.mvp.BaseView;

import java.util.List;


public interface WeChatWorldNewsContract {

	interface View extends BaseView {
		void refreshUI(List<WeChatNews> weChatNews);
	}

	abstract class Presenter extends BasePresenter<View> {
		public abstract void requestWorldNews(int page,int num);
	}
}
