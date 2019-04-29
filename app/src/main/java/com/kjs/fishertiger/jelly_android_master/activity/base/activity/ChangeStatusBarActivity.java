package com.kjs.fishertiger.jelly_android_master.activity.base.activity;

import android.content.Intent;
import android.view.View;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;

import butterknife.OnClick;


public class ChangeStatusBarActivity extends BaseActivity {

	private Intent intent;

	@Override
	public int getLayoutId() {
		return R.layout.activity_change_statusbar;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.status_bar_title));
		showHomeAsUp(R.mipmap.ic_back_btn);
	}

	@Override
	public void requestData() {

	}

	@OnClick({R.id.status_btn1, R.id.status_btn2,R.id.status_btn3,R.id.status_btn4})
	public void onMenuClick(View view) {
		switch (view.getId()) {
			case R.id.status_btn1:
				intent = new Intent(this, StatusBarColorActivity.class);
				intent.putExtra("type",0);
				startActivity(intent);
				break;
			case R.id.status_btn2:
				intent = new Intent(this, StatusBarColorActivity.class);
				intent.putExtra("type",1);
				startActivity(intent);
				break;
			case R.id.status_btn3:
				intent = new Intent(this, TransparentAndBlackFontStatusBarActivity.class);
				startActivity(intent);
				break;
			case R.id.status_btn4:
				intent = new Intent(this, TransparentStatusBarActivity.class);
				startActivity(intent);
				break;
		}

	}
}
