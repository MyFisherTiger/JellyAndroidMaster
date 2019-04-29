package com.kjs.fishertiger.jelly_android_master.activity.base.popupwindow;

import android.content.Context;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.base.BasePopupWindow;


public class PopupSlideBottom extends BasePopupWindow {

	public PopupSlideBottom(Context context) {
		super(context);
	}

	@Override
	public int getPopupLayoutRes() {
		return R.layout.popup_slide_bottom;
	}

	@Override
	public int getPopupAnimationStyleRes() {
		return R.style.animation_bottom;
	}
}
