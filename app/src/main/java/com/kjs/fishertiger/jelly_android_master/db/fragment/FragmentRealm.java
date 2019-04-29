package com.kjs.fishertiger.jelly_android_master.db.fragment;

import android.view.View;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseFragment;
import com.kjs.fishertiger.jelly_android_master.been.User;
import com.kjs.fishertiger.jellylibrary.db.DataManager;

import butterknife.OnClick;


public class FragmentRealm extends BaseFragment {

	private User user;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_realm;
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
				user=new User();
				user.setId("132323");
				user.setName("Young1");
				user.setAge(14);
				user.setAddress("广州市");
				DataManager.getInstance(DataManager.DataType.REALM).saveOrUpdateWithPKByRealm(user);
				showToast("保存成功");
				break;
			case R.id.queryBtn:
				user= (User) DataManager.getInstance(DataManager.DataType.REALM).queryFirstByRealm(User.class);
				String showContent="用户Id:"+user.getId()+"\n"+"用户姓名："+user.getName()+"\n"+"用户年龄："+user.getAge()+"\n"+"用户地址："+user.getAddress();
				showToast(showContent);
				break;
		}
	}

}
