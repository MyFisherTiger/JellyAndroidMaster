package com.kjs.fishertiger.jelly_android_master.mvp.presenter.wechat.okhttpcache;

import com.kjs.fishertiger.jelly_android_master.been.Result;
import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jelly_android_master.http.ApiClient;
import com.kjs.fishertiger.jelly_android_master.http.ApiUrl;
import com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache.WeChatNBANewsContract;
import com.kjs.fishertiger.jellylibrary.db.DataManager;
import com.kjs.fishertiger.jellylibrary.network.RequestBuilder;
import com.kjs.fishertiger.jellylibrary.network.rx.RxObservableListener;

import java.util.List;


public class WeChatNBANewsPresenter extends WeChatNBANewsContract.Presenter {
    @Override
    public void requestNBANews(int page, int num) {

        RequestBuilder<Result<List<WeChatNews>>> resultRequestBuilder = new RequestBuilder<>(new RxObservableListener<Result<List<WeChatNews>>>(mView) {
            @Override
            public void onNext(Result<List<WeChatNews>> result) {
                mView.refreshUI(result.getNewslist());
            }
        });

        resultRequestBuilder
                .setUrl(ApiUrl.URL_WETCHAT_NBA_NEWS)
                .setRequestParam(ApiClient.getRequiredBaseParam())
                .setTransformClass(WeChatNews.class)
                .setParam("page",page)
                .setParam("num",num);

        rxManager.addObserver(DataManager.getInstance(DataManager.DataType.RETROFIT).httpRequest(resultRequestBuilder));
    }
}
