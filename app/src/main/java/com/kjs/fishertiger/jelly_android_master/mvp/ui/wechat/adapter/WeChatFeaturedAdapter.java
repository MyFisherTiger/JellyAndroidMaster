package com.kjs.fishertiger.jelly_android_master.mvp.ui.wechat.adapter;

import android.content.Context;
import android.widget.ImageView;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.been.wechat.WeChatNews;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseViewHolder;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;
import com.kjs.fishertiger.jellylibrary.utils.GlideUtils;

import java.util.List;


public class WeChatFeaturedAdapter extends BaseRecyclerViewAdapter<WeChatNews> {

	public WeChatFeaturedAdapter(Context mContext, List<WeChatNews> mDatas, PullToRefreshRecyclerView pullToRefreshRecyclerView) {
		super(mContext, R.layout.item_wechat_featured, mDatas, pullToRefreshRecyclerView);
	}

	@Override
	protected void convert(BaseViewHolder baseViewHolder, WeChatNews weChatNews) {
		baseViewHolder.setText(R.id.weChatTitleTv,weChatNews.getTitle())
				.setText(R.id.weChatNameTv,weChatNews.getDescription())
				.setText(R.id.weChatTimeTv,weChatNews.getCtime());

		ImageView imageView=baseViewHolder.getView(R.id.weChatIv);
		GlideUtils.loadImg(mContext,weChatNews.getPicUrl(),R.mipmap.ic_bttom_loading_01,imageView);
	}
}
