package com.kjs.fishertiger.jelly_android_master.adapter.baseadapter;

import android.content.Context;
import android.view.View;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.been.ClickItem;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;

import java.util.List;


public class ItemClickAdapter extends BaseRecyclerViewAdapter<ClickItem> {

	private View.OnClickListener onClickListener;

	public ItemClickAdapter(Context mContext, List<ClickItem> mDatas, View.OnClickListener onClickListener) {
		super(mContext, R.layout.item_click, mDatas);
		this.onClickListener = onClickListener;
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, ClickItem clickItem) {
		baseViewHolder.setText(R.id.titleTv, clickItem.getTitle())
				.setImageResource(R.id.ivImg, clickItem.getRes())
				.setOnClickListener(R.id.clickTv, onClickListener);

		baseViewHolder.getView(R.id.clickTv).setVisibility(View.VISIBLE);
	}
}
