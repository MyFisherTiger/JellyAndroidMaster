package com.kjs.fishertiger.jellylibrary.network.rx;


import com.kjs.fishertiger.jellylibrary.network.NetWorkCodeException;

public interface ObservableListener<T> {
	void onNext(T result);
	void onComplete();
	void onError(NetWorkCodeException.ResponseThrowable e);
}
