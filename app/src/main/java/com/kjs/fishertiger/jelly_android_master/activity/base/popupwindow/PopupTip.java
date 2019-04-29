package com.kjs.fishertiger.jelly_android_master.activity.base.popupwindow;

import android.content.Context;
import android.view.ViewGroup;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.base.BasePopupWindow;


public class PopupTip extends BasePopupWindow {

	public PopupTip(Context context) {
		super(context, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		setShowMaskView(false);
	}

	@Override
	public int getPopupLayoutRes() {
		return R.layout.popup_tip;
	}

	@Override
	public int getPopupAnimationStyleRes() {
		return R.style.animation_scale;
	}
}
