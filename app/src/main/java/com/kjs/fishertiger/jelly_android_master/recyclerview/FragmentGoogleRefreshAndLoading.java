package com.kjs.fishertiger.jelly_android_master.recyclerview;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.recyclerview.GoogleRefreshAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseFragment;
import com.kjs.fishertiger.jelly_android_master.view.DefinitionAnimationLoadMoreView;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class FragmentGoogleRefreshAndLoading extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,PullToRefreshRecyclerView.OnRefreshAndLoadMoreListener{

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swl_Refresh;
    @BindView(R.id.recycler_rv)
    PullToRefreshRecyclerView mRecyclerView;

    private List<String> mDatas = new ArrayList<>();
    private GoogleRefreshAdapter googleRefreshAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_google_refresh;
    }

    @Override
    public void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setRefreshAndLoadMoreListener(this);
        mRecyclerView.setLoadMoreView(new DefinitionAnimationLoadMoreView(getActivity()));
        swl_Refresh.setColorSchemeResources(R.color.colorAccent);
        swl_Refresh.setOnRefreshListener(this);

    }

    @Override
    public void requestData() {
        for (int i = 0; i < 15; i++) {
            mDatas.add("Item" + (mDatas.size()+1));
        }
        refreshUI();
    }

    public void refreshUI() {
        if(googleRefreshAdapter==null){
            googleRefreshAdapter = new GoogleRefreshAdapter(getActivity(), mDatas, mRecyclerView);
            mRecyclerView.setAdapter(googleRefreshAdapter);
        }else{
            if (swl_Refresh!=null&&swl_Refresh.isRefreshing()) {
                swl_Refresh.setRefreshing(false);
                googleRefreshAdapter.notifyDataSetChanged();
            }

            if(mRecyclerView!=null&&mRecyclerView.isLoading()){
                mRecyclerView.loadMoreComplete();
            }
        }
    }

    @Override
    public void onRefresh() {

        if(mRecyclerView!=null&&mRecyclerView.isLoading()){
            swl_Refresh.setRefreshing(false);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDatas.clear();
                    requestData();
                }
            },3000);
        }

    }

    @Override
    public void onRecyclerViewRefresh() {

    }

    @Override
    public void onRecyclerViewLoadMore() {

        if(swl_Refresh.isRefreshing()){
            if(mRecyclerView!=null){
                mRecyclerView.loadMoreComplete();
            }
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                   requestData();
                }
            },3000);
        }

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
