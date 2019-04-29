package com.kjs.fishertiger.jelly_android_master.adapter.baseadapter;

import android.content.Context;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.been.MultiItem;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewMultiItemAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;
import com.kjs.fishertiger.jellylibrary.utils.DisplayUtils;

import java.util.List;


public class MultipleAdapter extends BaseRecyclerViewMultiItemAdapter<MultiItem> {

	private int mHeight;

	public MultipleAdapter(Context mContext, List<MultiItem> mDatas) {
		super(mContext, mDatas);
		mHeight = DisplayUtils.dip2px(mContext, 100);
		addItemType(MultiItem.TYPE_TEXT, R.layout.item_main);
		addItemType(MultiItem.TYPE_IMG, R.layout.item_img);
		addItemType(MultiItem.TYPE_TEXT_IMG, R.layout.item_click);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, MultiItem multiItem) {
		switch (baseViewHolder.getItemViewType()) {
			case MultiItem.TYPE_TEXT:
				baseViewHolder.getView(R.id.card_view).getLayoutParams().height = mHeight;
				baseViewHolder.setText(R.id.title, multiItem.getTitle());
				break;
			case MultiItem.TYPE_IMG:
				baseViewHolder.setImageResource(R.id.ivImg, multiItem.getRes());
				break;
			case MultiItem.TYPE_TEXT_IMG:
				baseViewHolder.setImageResource(R.id.ivImg, multiItem.getRes());
				baseViewHolder.setText(R.id.titleTv, multiItem.getTitle());
				break;

		}

	}
}
