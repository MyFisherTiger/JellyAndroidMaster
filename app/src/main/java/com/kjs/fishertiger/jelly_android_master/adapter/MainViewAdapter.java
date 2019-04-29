package com.kjs.fishertiger.jelly_android_master.adapter;

import android.content.Context;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;
import com.kjs.fishertiger.jellylibrary.utils.DisplayUtils;

import java.util.List;

public class MainViewAdapter extends BaseRecyclerViewAdapter<String> {

    private int mScreenWidth,mItemWidth;

    public MainViewAdapter(Context context, List<String> datas, PullToRefreshRecyclerView refreshRecyclerView) {
        super(context, R.layout.item_main,datas,refreshRecyclerView);
        mScreenWidth= DisplayUtils.getScreenWidthPixels(context);
        mItemWidth=(mScreenWidth-DisplayUtils.dip2px(context,20))/2;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.setText(R.id.title,s);
        baseViewHolder.getView(R.id.card_view).getLayoutParams().height=mItemWidth;
        baseViewHolder.getView(R.id.card_view).getLayoutParams().width=mItemWidth;
    }
}