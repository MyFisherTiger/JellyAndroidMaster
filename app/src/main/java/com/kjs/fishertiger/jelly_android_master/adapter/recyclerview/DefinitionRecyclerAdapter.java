package com.kjs.fishertiger.jelly_android_master.adapter.recyclerview;

import android.content.Context;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;

import java.util.List;


public class DefinitionRecyclerAdapter extends BaseRecyclerViewAdapter<String> {

    public DefinitionRecyclerAdapter(Context mContext, List<String> mDatas, PullToRefreshRecyclerView pullToRefreshRecyclerView) {
        super(mContext, R.layout.item_pull_refresh, mDatas, pullToRefreshRecyclerView);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.title,s);
    }
}
