package com.kjs.fishertiger.jellylibrary.refreshrecyclerview.defaultview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kjs.fishertiger.jellylibrary.R;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.refreshview.BaseLoadMoreView;

/**
 * 默认加载更多布局
 */

public class DefaultLoadMoreView extends BaseLoadMoreView {

	private TextView noDataTv;
	private LinearLayout loadMoreLl;

	public DefaultLoadMoreView(Context context) {
		super(context);
	}

	@Override
	public void initView(Context context){
		mContainer = LayoutInflater.from(context).inflate(R.layout.layout_default_loading_more, null);
		addView(mContainer);
		setGravity(Gravity.CENTER);
		noDataTv=mContainer.findViewById(R.id.no_data);
		loadMoreLl=mContainer.findViewById(R.id.loadMore_Ll);
	}

	@Override
	public void setState(int state) {
		this.setVisibility(VISIBLE);
		switch (state){
			case STATE_LOADING:
				loadMoreLl.setVisibility(VISIBLE);
				noDataTv.setVisibility(INVISIBLE);
				break;
			case STATE_COMPLETE:
				this.setVisibility(GONE);
				break;
			case STATE_NODATA:
				loadMoreLl.setVisibility(GONE);
				noDataTv.setVisibility(VISIBLE);
				break;
		}
		mState = state;

	}

	@Override
	public void destroy() {
	}

}
