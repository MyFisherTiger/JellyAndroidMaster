package com.kjs.fishertiger.jelly_android_master.customview.fragment;

import android.os.Bundle;
import android.widget.TextView;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseFragment;

import butterknife.BindView;


public class ChildFragment extends BaseFragment {

	@BindView(R.id.childTv)
	TextView childTv;

	public static ChildFragment newInstance(String str){
		ChildFragment childFragment = new ChildFragment();
		Bundle bundle = new Bundle();
		bundle.putString("str", str);
		childFragment.setArguments(bundle);
		return childFragment;
	}


	@Override
	public int getLayoutId() {
		return R.layout.fragment_child;
	}

	@Override
	public void init() {
		Bundle bundle = getArguments();
		String str = bundle.getString("str");
		childTv.setText(str);
	}

	@Override
	public void requestData() {

	}
}
