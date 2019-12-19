package com.kjs.fishertiger.jelly_android_master.activity;

import android.content.Intent;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.activity.base.activity.BaseUiActivity;
import com.kjs.fishertiger.jelly_android_master.activity.baseadapter.BaseAdapterActivity;
import com.kjs.fishertiger.jelly_android_master.activity.recyclerview.RecyclerViewActivity;
import com.kjs.fishertiger.jelly_android_master.adapter.MainViewAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.customview.activity.CustomViewActivity;
import com.kjs.fishertiger.jelly_android_master.db.activity.DataManagerActivity;
import com.kjs.fishertiger.jelly_android_master.mvp.ui.MVPActivity;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;
import com.kjs.fishertiger.jellylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements BaseRecyclerViewAdapter.OnItemClickListener {

	@BindView(R.id.recycler_rv)
	PullToRefreshRecyclerView mRecyclerView;

	private MainViewAdapter mainViewAdapter;
	private List<String> listData = new ArrayList<>();
	private Intent intent;

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void init() {

		setTitleContent(getString(R.string.activity_main_title));

		GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
		mRecyclerView.setLayoutManager(layoutManager);
		View header = LayoutInflater.from(this).inflate(R.layout.layout_main_header, null);
		mRecyclerView.addHeaderView(header);
	}

	@Override
	public void requestData() {
		listData.add("RecyclerView");
		listData.add("BaseAdapter");
		listData.add("MVP+RxJava+Retrofit");
		listData.add("DataManager(Retrofit/SharePreference/Realm)");
		listData.add("Base");
		listData.add("CustomView");
		refreshUI();
	}

	public void refreshUI() {

		mainViewAdapter = new MainViewAdapter(this, listData, mRecyclerView);
		mRecyclerView.setAdapter(mainViewAdapter);
		mainViewAdapter.setOnItemClickListener(this);
	}

	private long currentTime;

	@Override
	public void onBackPressed() {

		if (System.currentTimeMillis() > currentTime) {
			ToastUtils.showToast(MainActivity.this,"再按一次即可退出");
		} else {
			super.onBackPressed();
		}
		currentTime = System.currentTimeMillis() + 2000;

	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mRecyclerView != null) {
			mRecyclerView.destroy();
			mRecyclerView = null;
		}
	}

	@Override
	public void onItemClick(View view, int position) {
		switch (position) {
			case 0:
				intent = new Intent(this, RecyclerViewActivity.class);
				startActivity(intent);
				break;
			case 1:
				intent = new Intent(this, BaseAdapterActivity.class);
				startActivity(intent);
				break;
			case 2:
				intent = new Intent(this, MVPActivity.class);
				startActivity(intent);
				break;
			case 3:
				intent = new Intent(this, DataManagerActivity.class);
				startActivity(intent);
				break;
			case 4:
				intent = new Intent(this, BaseUiActivity.class);
				startActivity(intent);
				break;
			case 5:
				intent = new Intent(this, CustomViewActivity.class);
				startActivity(intent);
				break;
		}
	}
}
