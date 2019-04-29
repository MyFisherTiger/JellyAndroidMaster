package com.kjs.fishertiger.jellylibrary.network.rx;

import android.text.TextUtils;

import com.kjs.fishertiger.jellylibrary.mvp.BaseView;
import com.kjs.fishertiger.jellylibrary.network.NetWorkCodeException;


public abstract class RxObservableListener<T> implements ObservableListener<T>{


	private BaseView mView;
	private String mErrorMsg;

	protected RxObservableListener(BaseView view){
		this.mView = view;
	}

	protected RxObservableListener(BaseView view, String errorMsg){
		this.mView = view;
		this.mErrorMsg = errorMsg;
	}

	@Override
	public void onComplete() {

	}

	@Override
	public void onError(NetWorkCodeException.ResponseThrowable e) {
		if (mView == null) {
			return;
		} if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
			mView.onError(mErrorMsg);
		}else {
			mView.onError(e.message);
		}
	}
}
