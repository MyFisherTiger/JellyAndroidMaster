package com.kjs.fishertiger.jelly_android_master.activity.base.adapter;

import android.content.Context;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;

import java.util.List;


public class PopupMenuListAdapter extends BaseRecyclerViewAdapter<String> {
	public PopupMenuListAdapter(Context mContext,List<String> mDatas) {
		super(mContext, R.layout.item_popup, mDatas);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, String s) {
		baseViewHolder.setText(R.id.tvTxt,s);
	}
}
