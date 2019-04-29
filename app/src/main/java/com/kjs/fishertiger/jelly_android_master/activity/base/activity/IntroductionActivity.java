package com.kjs.fishertiger.jelly_android_master.activity.base.activity;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;

public class IntroductionActivity extends BaseActivity {

	@Override
	public int getLayoutId() {
		return R.layout.layout_introduction;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.activity_base_ui_title));
		showHomeAsUp(R.mipmap.ic_back_btn);
	}

	@Override
	public void requestData() {
	}
}
