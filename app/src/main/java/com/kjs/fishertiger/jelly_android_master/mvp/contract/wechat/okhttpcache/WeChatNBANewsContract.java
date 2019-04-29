package com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache;

import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jellylibrary.mvp.BasePresenter;
import com.kjs.fishertiger.jellylibrary.mvp.BaseView;

import java.util.List;


public interface WeChatNBANewsContract {

	interface View extends BaseView {
		void refreshUI(List<WeChatNews> newsLists);
	}

	abstract class Presenter extends BasePresenter<View> {
		public abstract void requestNBANews(int page,int num);
	}
}
