package com.kjs.fishertiger.jelly_android_master.mvp.presenter.wechat.okhttpcache;


import com.kjs.fishertiger.jelly_android_master.been.WeChatNewsResult;
import com.kjs.fishertiger.jelly_android_master.http.ApiClient;
import com.kjs.fishertiger.jelly_android_master.http.ApiUrl;
import com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache.WeChatFeaturedContract;
import com.kjs.fishertiger.jellylibrary.db.DataManager;
import com.kjs.fishertiger.jellylibrary.network.RequestBuilder;
import com.kjs.fishertiger.jellylibrary.network.rx.RxObservableListener;


public class WeChatFeaturedNoCommonClassPresenter extends WeChatFeaturedContract.Presenter {
	@Override
	public void requestFeaturedNews(int page, int num) {

		RequestBuilder<WeChatNewsResult> resultRequestBuilder = new RequestBuilder<>(new RxObservableListener<WeChatNewsResult>(mView) {
			@Override
			public void onNext(WeChatNewsResult result) {
				mView.refreshUI(result.getNewslist());
			}
		});

		resultRequestBuilder
				.setUrl(ApiUrl.URL_WETCHAT_FEATURED)
				.setTransformClass(WeChatNewsResult.class)
				.setUserCommonClass(false)
				.setRequestParam(ApiClient.getRequiredBaseParam())
				.setParam("page",page)
				.setParam("num",num);

		rxManager.addObserver(DataManager.getInstance(DataManager.DataType.RETROFIT).httpRequest(resultRequestBuilder));

	}
}
