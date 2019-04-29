package com.kjs.fishertiger.jelly_android_master.adapter.baseadapter;

import android.content.Context;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;
import com.kjs.fishertiger.jellylibrary.utils.DisplayUtils;

import java.util.List;

public class DragAndDeleteAdapter extends BaseRecyclerViewAdapter<String> {

	private int mHeight;

	public DragAndDeleteAdapter(Context mContext, List<String> mDatas) {
		super(mContext, R.layout.item_main, mDatas);
		mHeight= DisplayUtils.dip2px(mContext,100);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, String s) {
		baseViewHolder.setText(R.id.title,s);
		baseViewHolder.getView(R.id.card_view).getLayoutParams().height=mHeight;
	}
}
