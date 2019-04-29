package com.kjs.fishertiger.jelly_android_master.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.refreshview.BaseLoadMoreView;


public class DefinitionAnimationLoadMoreView extends BaseLoadMoreView {

	private ImageView loadingIv;
	private TextView noDataTv;
	private LinearLayout loadMore_Ll;
	//动画
	private AnimationDrawable animationDrawable;

	public DefinitionAnimationLoadMoreView(Context context) {
		super(context);
	}

	@Override
	public void initView(Context context) {
		mContainer = LayoutInflater.from(context).inflate(R.layout.layout_definition_animation_loading_more, null);
		addView(mContainer);
		setGravity(Gravity.CENTER);

		loadingIv=mContainer.findViewById(R.id.loadingIv);
		noDataTv=mContainer.findViewById(R.id.no_data);
		loadMore_Ll=mContainer.findViewById(R.id.loadMore_Ll);
	}

	@Override
	public void setState(int state) {
		switch (state){
			case STATE_LOADING:
				loadMore_Ll.setVisibility(VISIBLE);
				noDataTv.setVisibility(INVISIBLE);
				animationDrawable= (AnimationDrawable) loadingIv.getDrawable();
				animationDrawable.start();
				this.setVisibility(VISIBLE);
				break;
			case STATE_COMPLETE:
				if(animationDrawable!=null){
					animationDrawable.stop();
				}
				this.setVisibility(GONE);
				break;
			case STATE_NODATA:
				loadMore_Ll.setVisibility(INVISIBLE);
				noDataTv.setVisibility(VISIBLE);
				animationDrawable= (AnimationDrawable) loadingIv.getDrawable();
				animationDrawable.start();
				this.setVisibility(VISIBLE);
				break;
		}

		mState = state;

	}
	@Override
	public void destroy() {

	}
}
