package com.kjs.fishertiger.jellylibrary.db.helper;

import com.kjs.fishertiger.jellylibrary.network.RequestBuilder;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;


public interface HttpHelper {
    <T> DisposableObserver<ResponseBody> httpRequest(RequestBuilder<T> requestBuilder);
}
