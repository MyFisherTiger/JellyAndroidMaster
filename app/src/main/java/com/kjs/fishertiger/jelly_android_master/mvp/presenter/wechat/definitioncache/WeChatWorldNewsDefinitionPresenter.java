package com.kjs.fishertiger.jelly_android_master.mvp.presenter.wechat.definitioncache;

import com.kjs.fishertiger.jelly_android_master.been.Result;
import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jelly_android_master.common.AppConfig;
import com.kjs.fishertiger.jelly_android_master.http.ApiClient;
import com.kjs.fishertiger.jelly_android_master.http.ApiUrl;
import com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache.WeChatWorldNewsContract;
import com.kjs.fishertiger.jellylibrary.db.DataManager;
import com.kjs.fishertiger.jellylibrary.network.RequestBuilder;
import com.kjs.fishertiger.jellylibrary.network.rx.RxObservableListener;

import java.util.List;



public class WeChatWorldNewsDefinitionPresenter extends WeChatWorldNewsContract.Presenter {
    @Override
    public void requestWorldNews(int page, int num) {
        String filePath= AppConfig.STORAGE_DIR+"wechat/world";
        String fileName=page+".t";

        RequestBuilder<Result<List<WeChatNews>>> resultRequestBuilder = new RequestBuilder<>(new RxObservableListener<Result<List<WeChatNews>>>(mView) {
            @Override
            public void onNext(Result<List<WeChatNews>> result) {
                mView.refreshUI(result.getNewslist());
            }
        });

        resultRequestBuilder
                .setFilePathAndFileName(filePath,fileName)
                .setTransformClass(WeChatNews.class)
                .setUrl(ApiUrl.URL_WETCHAT_WORLD_NEWS)
                .setRequestParam(ApiClient.getRequiredBaseParam())
                .setParam("page",page)
                .setParam("num",num)
                .setHttpTypeAndReqType(RequestBuilder.HttpType.DEFAULT_GET,RequestBuilder.ReqType.DISK_CACHE_NO_NETWORK_LIST)
        ;

        rxManager.addObserver(DataManager.getInstance(DataManager.DataType.RETROFIT).httpRequest(resultRequestBuilder));
    }
}
