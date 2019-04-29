package com.kjs.fishertiger.jelly_android_master.adapter.recyclerview;

import android.content.Context;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;
import com.kjs.fishertiger.jellylibrary.utils.DisplayUtils;

import java.util.List;


public class DefaultRecyclerAdapter extends BaseRecyclerViewAdapter<String> {

    private int mScreenWidth,mItemWidth;

    public DefaultRecyclerAdapter(Context mContext, List<String> mDatas, PullToRefreshRecyclerView pullToRefreshRecyclerView) {
        super(mContext, R.layout.item_pull_refresh, mDatas, pullToRefreshRecyclerView);
        mScreenWidth= DisplayUtils.getScreenWidthPixels(mContext);
        mItemWidth=(mScreenWidth-DisplayUtils.dip2px(mContext,30))/3;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.title,s);
        baseViewHolder.getView(R.id.card_view).getLayoutParams().height=mItemWidth;
        baseViewHolder.getView(R.id.card_view).getLayoutParams().width=mItemWidth;
    }
}
