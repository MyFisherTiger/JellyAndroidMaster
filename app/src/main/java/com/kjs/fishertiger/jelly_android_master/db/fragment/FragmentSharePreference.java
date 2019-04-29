package com.kjs.fishertiger.jelly_android_master.db.fragment;

import android.view.View;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseFragment;
import com.kjs.fishertiger.jellylibrary.db.DataManager;

import butterknife.OnClick;


public class FragmentSharePreference extends BaseFragment {

	@Override
	public int getLayoutId() {
		return R.layout.fragment_sharepreference;
	}

	@Override
	public void init() {
	}

	@Override
	public void requestData() {
	}


	@OnClick({R.id.saveBtn,R.id.queryBtn})
	public void onMenuClick(View view){
		switch (view.getId()){
			case R.id.saveBtn:
				DataManager.getInstance(DataManager.DataType.SHAREPREFERENCE).saveByKeyWithSP("user","这是一条测试的内容");
				showToast("保存成功");
				break;
			case R.id.queryBtn:
				String user=DataManager.getInstance(DataManager.DataType.SHAREPREFERENCE).queryByKeyWithSP("user",String.class,"");
				showToast(user);
				break;
		}
	}

}
