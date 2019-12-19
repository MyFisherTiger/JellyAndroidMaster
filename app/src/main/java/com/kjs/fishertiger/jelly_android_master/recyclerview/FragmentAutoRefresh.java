package com.kjs.fishertiger.jelly_android_master.recyclerview;

import android.os.Handler;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.recyclerview.DefinitionRecyclerAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseFragment;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FragmentAutoRefresh extends BaseFragment implements PullToRefreshRecyclerView.OnRefreshAndLoadMoreListener {

	@BindView(R.id.recycler_rv)
	PullToRefreshRecyclerView mRecyclerView;

	private DefinitionRecyclerAdapter definitionRefreshAdapter;
	private List<String> mDatas = new ArrayList<>();

	@Override
	public int getLayoutId() {
		return R.layout.fragment_nomore_auto;
	}

	@Override
	public void init() {
		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		mRecyclerView.setPullRefreshEnabled(true);
		mRecyclerView.setLoadMoreEnabled(true);
		mRecyclerView.setRefreshAndLoadMoreListener(this);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);

		if (isVisibleToUser) {
			if (mRecyclerView != null && !mRecyclerView.isRefreshing()) {
				autoRefresh();
			}
		}
	}

	@Override
	public void requestData() {


		for (int i = 0; i < 10; i++) {
			mDatas.add("item" + (mDatas.size() + 1));
		}
		refreshUI();


	}

	private void autoRefresh() {
		mRecyclerView.setAutoRefresh();
	}

	public void refreshUI() {
		if (definitionRefreshAdapter == null) {
			definitionRefreshAdapter = new DefinitionRecyclerAdapter(getActivity(), mDatas, mRecyclerView);
			mRecyclerView.setAdapter(definitionRefreshAdapter);
		} else {
			if (mRecyclerView != null) {
				if (mRecyclerView.isLoading()) {
					mRecyclerView.loadMoreComplete();
				} else if (mRecyclerView.isRefreshing()) {
					mRecyclerView.refreshComplete();
				}
			}
		}
	}

	@Override
	public void onRecyclerViewRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mDatas.clear();
				requestData();
			}
		}, 3000);
	}

	@Override
	public void onRecyclerViewLoadMore() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				requestData();
			}
		}, 3000);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mRecyclerView != null){
			mRecyclerView.destroy();
			mRecyclerView = null;
		}
	}
}
