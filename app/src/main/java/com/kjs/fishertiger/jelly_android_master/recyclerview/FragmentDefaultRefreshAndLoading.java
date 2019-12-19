package com.kjs.fishertiger.jelly_android_master.recyclerview;

import android.os.Handler;
import androidx.recyclerview.widget.GridLayoutManager;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.recyclerview.DefaultRecyclerAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseFragment;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FragmentDefaultRefreshAndLoading extends BaseFragment implements PullToRefreshRecyclerView.OnRefreshAndLoadMoreListener {

	@BindView(R.id.recycler_rv)
	PullToRefreshRecyclerView mRecyclerView;

	private DefaultRecyclerAdapter defaultRefreshAdapter;
	private List<String> mDatas = new ArrayList<>();

	@Override
	public int getLayoutId() {
		return R.layout.fragment_default_refresh;
	}

	@Override
	public void init() {
		GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
		mRecyclerView.setLayoutManager(layoutManager);
		mRecyclerView.setPullRefreshEnabled(true);
		mRecyclerView.setLoadMoreEnabled(true);
		mRecyclerView.setRefreshAndLoadMoreListener(this);
	}

	@Override
	public void requestData() {
		for (int i = 0; i < 15; i++) {
			mDatas.add("Item" + (mDatas.size() + 1));
		}
		refreshUI();
	}

	public void refreshUI() {
		if (defaultRefreshAdapter == null) {
			defaultRefreshAdapter = new DefaultRecyclerAdapter(getActivity(), mDatas, mRecyclerView);
			mRecyclerView.setAdapter(defaultRefreshAdapter);
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
