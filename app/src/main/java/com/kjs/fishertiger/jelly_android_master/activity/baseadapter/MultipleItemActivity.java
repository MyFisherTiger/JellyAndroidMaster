package com.kjs.fishertiger.jelly_android_master.activity.baseadapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.baseadapter.MultipleAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.been.MultiItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MultipleItemActivity extends BaseActivity {

	@BindView(R.id.recycler_rv)
	RecyclerView mRecyclerView;
	private MultipleAdapter multiAdapter;

	private List<MultiItem> mDatas = new ArrayList<>();

	@Override
	public int getLayoutId() {
		return R.layout.layout_recyclerview;
	}

	@Override
	public void init() {

		setTitleContent(getString(R.string.activity_multiple_title));
		showHomeAsUp(R.mipmap.ic_back_btn);

		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layoutManager);

	}

	@Override
	public void requestData() {
		for (int i = 0; i < 20; i++) {
			MultiItem multiItem = new MultiItem();
			multiItem.setTitle("第" + i + "个item");
			multiItem.setRes(R.mipmap.header);

			if (i < 10) {
				if (i % 3 == 0) {
					multiItem.setType(MultiItem.TYPE_TEXT);
				} else if (i % 3 == 1) {
					multiItem.setType(MultiItem.TYPE_IMG);
				} else {
					multiItem.setType(MultiItem.TYPE_TEXT_IMG);
				}
			} else {
				if (i % 3 == 1) {
					multiItem.setType(MultiItem.TYPE_TEXT);
				} else if (i % 3 == 0) {
					multiItem.setType(MultiItem.TYPE_IMG);
				} else {
					multiItem.setType(MultiItem.TYPE_TEXT_IMG);
				}
			}
			mDatas.add(multiItem);
		}


		multiAdapter = new MultipleAdapter(this, mDatas);
		mRecyclerView.setAdapter(multiAdapter);

	}
}
