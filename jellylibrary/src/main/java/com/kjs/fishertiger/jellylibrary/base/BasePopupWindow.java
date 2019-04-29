package com.kjs.fishertiger.jellylibrary.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.IBinder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

public abstract class BasePopupWindow extends PopupWindow {
	public Context context;
	public View popupView;
	private boolean isShowMaskView=false;
	private View maskView;
	private WindowManager windowManager;

	public BasePopupWindow(Context context) {
		this.context=context;
		initView(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
	}

	public BasePopupWindow(Context context, int w, int h){
		this.context=context;
		initView(context,w,h);
	}

	private void initView(Context context, int w, int h){
		windowManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		if(getPopupLayoutRes()!=0) {
			popupView = LayoutInflater.from(context).inflate(getPopupLayoutRes(), null);
			this.setContentView(popupView);
			this.setWidth(w);
			this.setHeight(h);
			setFocusable(true);
			setOutsideTouchable(true);
			setBackgroundDrawable(new ColorDrawable());
			if(getPopupAnimationStyleRes()!=0){
				setAnimationStyle(getPopupAnimationStyleRes());
			}

			popupView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					dismiss();
				}
			});
		}

	}

	public void showPopup(){
		View anchor=((Activity)context).findViewById(android.R.id.content);
		if(isShowMaskView){
			addMask(anchor.getWindowToken());
		}
		this.showAtLocation(anchor, Gravity.CENTER,0,0);
	}

	public void showPopupAsDropDown(View anchor){
		if(isShowMaskView){
			addMask(anchor.getWindowToken());
		}
		this.showAsDropDown(anchor);
	}

	public void setShowMaskView(boolean isShowMaskView){
		this.isShowMaskView=isShowMaskView;
	}

	private void addMask(IBinder token) {
		WindowManager.LayoutParams wl = new WindowManager.LayoutParams();
		wl.width = WindowManager.LayoutParams.MATCH_PARENT;
		wl.height = WindowManager.LayoutParams.MATCH_PARENT;
		wl.format = PixelFormat.TRANSLUCENT;//不设置这个弹出框的透明遮罩显示为黑色
		wl.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;//该Type描述的是形成的窗口的层级关系
		wl.token = token;//获取当前Activity中的View中的token,来依附Activity
		maskView = new View(context);
		maskView.setBackgroundColor(0x7f000000);
		maskView.setFitsSystemWindows(false);
		maskView.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					removeMask();
					return true;
				}
				return false;
			}
		});
		/**
		 * 通过WindowManager的addView方法创建View，产生出来的View根据WindowManager.LayoutParams属性不同，效果也就不同了。
		 * 比如创建系统顶级窗口，实现悬浮窗口效果！
		 */
		windowManager.addView(maskView, wl);
	}

	private void removeMask() {
		if (null != maskView) {
			windowManager.removeViewImmediate(maskView);
			maskView = null;
		}
	}

	@Override
	public void dismiss() {
		if(isShowMaskView){
			removeMask();
		}
		super.dismiss();
	}


	public abstract int getPopupLayoutRes();
	public abstract int getPopupAnimationStyleRes();
}
