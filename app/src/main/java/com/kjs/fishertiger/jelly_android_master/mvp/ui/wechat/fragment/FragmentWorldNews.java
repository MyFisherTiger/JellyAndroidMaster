package com.kjs.fishertiger.jelly_android_master.mvp.ui.wechat.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseFragment;
import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jelly_android_master.mvp.contract.wechat.okhttpcache.WeChatWorldNewsContract;
import com.kjs.fishertiger.jelly_android_master.mvp.presenter.wechat.okhttpcache.WeChatWorldNewsPresenter;
import com.kjs.fishertiger.jelly_android_master.mvp.ui.wechat.adapter.WeChatFeaturedAdapter;
import com.kjs.fishertiger.jellylibrary.base.StateView;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FragmentWorldNews extends BaseFragment<WeChatWorldNewsPresenter> implements
		WeChatWorldNewsContract.View, PullToRefreshRecyclerView.OnRefreshAndLoadMoreListener,
		BaseRecyclerViewAdapter.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {

	@BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swl_Refresh;
	@BindView(R.id.refreshRv)
	PullToRefreshRecyclerView refreshRv;
	@BindView(R.id.state_view)
	StateView stateView;

	private static final int PAGE_SIZE = 15;
	private int pageSize = 1;

	private List<WeChatNews> mDatas = new ArrayList<>();
	private WeChatFeaturedAdapter weChatFeaturedAdapter;

	@Override
	public int getLayoutId() {
		return R.layout.fragment_wechat_news1;
	}

	@Override
	public void init() {
		stateView.showViewByState(StateView.STATE_LOADING);
		stateView.setOnDisConnectViewListener(new StateView.OnDisConnectListener() {
			@Override
			public void onDisConnectViewClick() {
				requestData();
			}
		});


		LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		refreshRv.setLayoutManager(linearLayoutManager);
		refreshRv.setPullRefreshEnabled(false);
		refreshRv.setLoadMoreEnabled(true);
		refreshRv.setRefreshAndLoadMoreListener(this);
		swl_Refresh.setColorSchemeResources(R.color.colorAccent);
		swl_Refresh.setOnRefreshListener(this);
	}

	@Override
	public void requestData() {
		((WeChatWorldNewsPresenter) mPresenter).requestWorldNews(pageSize, PAGE_SIZE);
	}

	@Override
	public void refreshUI(List<WeChatNews> newsList) {

		if (newsList != null) {
			if (pageSize == 1) {
				mDatas.clear();
				mDatas.addAll(newsList);
			} else {
				mDatas.addAll(newsList);
			}

		}

		if (weChatFeaturedAdapter == null) {
			if (mDatas.size() == 0) {
				stateView.showViewByState(StateView.STATE_EMPTY);
			} else {
				stateView.showViewByState(StateView.STATE_NO_DATA);
			}
			weChatFeaturedAdapter = new WeChatFeaturedAdapter(getActivity(), mDatas, refreshRv);
			refreshRv.setAdapter(weChatFeaturedAdapter);
		} else {

			if (swl_Refresh!=null&&swl_Refresh.isRefreshing()) {
				swl_Refresh.setRefreshing(false);
				weChatFeaturedAdapter.notifyDataSetChanged();
			}else if (refreshRv.isLoading()) {
				refreshRv.loadMoreComplete();
				if (newsList==null||newsList.size() == 0) {
					refreshRv.setNoMoreDate(true);
				}
			}
		}
	}


	@Override
	public void onItemClick(View view, int position) {

	}

	@Override
	public void onRefresh() {
		pageSize = 1;
		requestData();
	}

	@Override
	public void onRecyclerViewRefresh() {

	}

	@Override
	public void onRecyclerViewLoadMore() {
		pageSize++;
		requestData();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (refreshRv != null) {
			refreshRv.destroy();
			refreshRv = null;
		}
	}

	@Override
	public void onError(String errorMsg) {
		showToast(errorMsg);
		if(mDatas.size()==0){
			stateView.showViewByState(StateView.STATE_DISCONNECT);
		}
	}
}
