package com.kjs.fishertiger.jelly_android_master.activity.base.activity;

import android.widget.LinearLayout;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.activity.base.popupwindow.PopupMenuList;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class PopupMenuActivity extends BaseActivity {

	@BindView(R.id.clickLl)
	LinearLayout clickLl;
	@Override
	public int getLayoutId() {
		return R.layout.activity_popup_list;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.popup_title));
		showHomeAsUp(R.mipmap.ic_back_btn);
	}

	@Override
	public void requestData() {

	}

	@OnClick(R.id.clickLl)
	public void onMenClick(){
		PopupMenuList popupMenuList=new PopupMenuList(this);
		popupMenuList.showPopupAsDropDown(clickLl);
	}
}
