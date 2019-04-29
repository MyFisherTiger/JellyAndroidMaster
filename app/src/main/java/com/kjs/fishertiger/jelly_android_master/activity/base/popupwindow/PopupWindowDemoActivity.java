package com.kjs.fishertiger.jelly_android_master.activity.base.popupwindow;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.activity.base.activity.PopupMenuActivity;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class PopupWindowDemoActivity extends BaseActivity {

	@BindView(R.id.status_btn2)
	Button statusBtn;

	@Override
	public int getLayoutId() {
		return R.layout.activity_popupwindow;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.popup_title));
		showHomeAsUp(R.mipmap.ic_back_btn);
	}

	@Override
	public void requestData() {

	}


	@OnClick({R.id.status_btn1, R.id.status_btn2,R.id.status_btn3,R.id.status_btn4})
	public void onMenuClick(View view) {
		switch (view.getId()) {
			case R.id.status_btn1:
				PopupSlideBottom popupSlideBottom=new PopupSlideBottom(this);
				popupSlideBottom.showPopup();
				break;
			case R.id.status_btn2:
				PopupSlideButton popupSlideButton=new PopupSlideButton(this);
				popupSlideButton.showPopupAsDropDown(statusBtn);
				break;

			case R.id.status_btn3:
				PopupTip popupTip=new PopupTip(this);
				popupTip.showPopup();
				break;
			case R.id.status_btn4:
				Intent intent=new Intent(this, PopupMenuActivity.class);
				startActivity(intent);
				break;
		}

	}

}
