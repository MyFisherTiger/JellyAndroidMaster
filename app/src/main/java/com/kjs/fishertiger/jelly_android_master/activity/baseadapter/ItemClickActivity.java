package com.kjs.fishertiger.jelly_android_master.activity.baseadapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.baseadapter.ItemClickAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.been.ClickItem;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class ItemClickActivity extends BaseActivity implements BaseRecyclerViewAdapter.OnItemClickListener,BaseRecyclerViewAdapter.onItemLongClickListener,View.OnClickListener{

	@BindView(R.id.recycler_rv)
	RecyclerView mRecyclerView;

	private List<ClickItem> mDatas=new ArrayList<>();
	private ItemClickAdapter itemClickAdapter;

	@Override
	public int getLayoutId() {
		return R.layout.layout_recyclerview;
	}

	@Override
	public void init() {

		setTitleContent(getString(R.string.activity_item_click_title));
		showHomeAsUp(R.mipmap.ic_back_btn);


		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

		mRecyclerView.setLayoutManager(layoutManager);
		for(int i=0;i<20;i++){
			ClickItem clickItem=new ClickItem();
			clickItem.setTitle("第"+(i+1)+"个item");
			clickItem.setRes(R.mipmap.header);
			mDatas.add(clickItem);
		}
		itemClickAdapter=new ItemClickAdapter(this,mDatas,this);

		mRecyclerView.setAdapter(itemClickAdapter);
		itemClickAdapter.setOnItemClickListener(this);
		itemClickAdapter.setOnItemLongClickListener(this);

	}

	@Override
	public void requestData() {

	}

	@Override
	public void onClick(View v) {
		ToastUtils.showToast(ItemClickActivity.this,"其实点击我没有奖");
	}

	@Override
	public void onItemClick(View view, int position) {
		ToastUtils.showToast(ItemClickActivity.this,mDatas.get(position).getTitle());
	}

	@Override
	public boolean onItemLongClick(View view, int position) {
		ToastUtils.showToast(ItemClickActivity.this,"进行长按操作");
		return true;
	}
}
