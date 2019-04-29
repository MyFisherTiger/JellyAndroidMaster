package com.kjs.fishertiger.jellylibrary.network.rx;

import com.kjs.fishertiger.jellylibrary.network.NetWorkCodeException;

import io.reactivex.observers.DisposableObserver;

/**
 * Observer的处理事件
 */

public abstract class RxSubscriber<T> extends DisposableObserver<T>{

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        _onError(NetWorkCodeException.getResponseThrowable(e));
    }

    @Override
    public void onComplete() {
        _onComplete();
    }

    /**
     * 定义处理事件
     */
    public abstract void _onNext(T t);
    public abstract void _onError(NetWorkCodeException.ResponseThrowable e);
    public abstract void _onComplete();
}
