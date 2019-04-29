package com.kjs.fishertiger.jelly_android_master.mvp.presenter.wechat.definitioncache;


import com.kjs.fishertiger.jelly_android_master.been.Result;
import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jelly_android_master.common.AppConfig;
import com.kjs.fishertiger.jelly_android_master.http.ApiClient;
import com.kjs.fishertiger.jelly_android_master.http.ApiUrl;
import com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache.WeChatChinaNewsContract;
import com.kjs.fishertiger.jellylibrary.db.DataManager;
import com.kjs.fishertiger.jellylibrary.network.RequestBuilder;
import com.kjs.fishertiger.jellylibrary.network.rx.RxObservableListener;

import java.util.List;


public class WeChatChinaNewsDefinitionPresenter extends WeChatChinaNewsContract.Presenter {
	@Override
	public void requestChinaNews(int page, int num) {
		String filePath = AppConfig.STORAGE_DIR + "wechat/china";
		String fileName = "limttime.t";

		RequestBuilder resultRequestBuilder = new RequestBuilder<>(new RxObservableListener<Result<List<WeChatNews>>>(mView) {
			@Override
			public void onNext(Result<List<WeChatNews>> result) {
				mView.refreshUI(result.getNewslist());
			}
		}).setFilePathAndFileName(filePath, fileName)
				.setTransformClass(WeChatNews.class)
				.setUrl(ApiUrl.URL_WETCHAT_CHINA_NEWS)
				.setRequestParam(ApiClient.getRequiredBaseParam())
				.setHttpTypeAndReqType(RequestBuilder.HttpType.DEFAULT_GET, RequestBuilder.ReqType.DISK_CACHE_LIST_LIMIT_TIME)
				.setParam("page", page)
				.setParam("num", num);

		rxManager.addObserver(DataManager.getInstance(DataManager.DataType.RETROFIT).httpRequest(resultRequestBuilder));
	}
}
