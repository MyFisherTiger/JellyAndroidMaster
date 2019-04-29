package com.kjs.fishertiger.jelly_android_master.activity.base.activity;

import android.view.View;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jellylibrary.utils.DisplayUtils;

import butterknife.OnClick;


public class StatusBarColorActivity extends BaseActivity {

	int []colors={R.color.colorAccent,R.color.colorPrimaryDark,R.color.colorPrimary};
	private int clickCount=1;
	private int type;

	@Override
	public int getLayoutId() {
		return R.layout.activity_color_statusbar;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.status_bar_bg));
		showHomeAsUp(R.mipmap.ic_back_btn);
		type=getIntent().getIntExtra("type",0);
		if(type==0){
			DisplayUtils.setStatusBarBlackFontBgColor(this,colors[0]);
		}else{
			DisplayUtils.setStatusBarColor(this,colors[0]);
		}
	}

	@OnClick({R.id.status_btn1})
	public void onMenuClick(View view) {

		switch (type){
			case 0:
				if(clickCount%3==0){
					DisplayUtils.setStatusBarBlackFontBgColor(this,colors[0]);
				}else if(clickCount%3==1){
					DisplayUtils.setStatusBarBlackFontBgColor(this,colors[1]);
				}else{
					DisplayUtils.setStatusBarBlackFontBgColor(this,colors[2]);
				}
				break;
			case 1:
				if(clickCount%3==0){
					DisplayUtils.setStatusBarColor(this,colors[0]);
				}else if(clickCount%3==1){
					DisplayUtils.setStatusBarColor(this,colors[1]);
				}else{
					DisplayUtils.setStatusBarColor(this,colors[2]);
				}
				break;
		}

		clickCount++;
	}

	@Override
	public void requestData() {

	}
}
