package com.kjs.fishertiger.jellylibrary.mvp;


import com.kjs.fishertiger.jellylibrary.network.rx.RxManager;

public abstract class BasePresenter<T> {
    public T mView;

    public RxManager rxManager=new RxManager();

    public void setV(T v){
        this.mView=v;
    }

    public void onDestroy(){
        rxManager.clear();
        rxManager=null;
    }
}
