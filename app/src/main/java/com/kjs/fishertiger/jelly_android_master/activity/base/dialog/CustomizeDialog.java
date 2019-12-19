package com.kjs.fishertiger.jelly_android_master.activity.base.dialog;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.activity.base.adapter.ItemRecycleAdapter;
import com.kjs.fishertiger.jellylibrary.base.BaseDialog;
import com.kjs.fishertiger.jellylibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.kjs.fishertiger.jellylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**自定义dialog样式
 *
 */

public class CustomizeDialog extends BaseDialog implements BaseRecyclerViewAdapter.OnItemClickListener{


	@BindView(R.id.rv_Item)
	protected RecyclerView rv_Item;

	private ItemRecycleAdapter itemRecycleAdapter;
	private List<String> itemStr=new ArrayList<>();

	public CustomizeDialog(Context context) {
		super(context);
		setContentView(R.layout.dialog_list);

	}

	@Override
	protected void initUI() {
		ButterKnife.bind(this,mainView);
		rv_Item.setLayoutManager(new LinearLayoutManager(context));
		for(int i=0;i<10;i++){
			itemStr.add("这是第"+(i+1)+"个item");
		}
		itemRecycleAdapter=new ItemRecycleAdapter(context,itemStr);
		rv_Item.setAdapter(itemRecycleAdapter);
		itemRecycleAdapter.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(View view, int position) {
		ToastUtils.showToast(context,itemStr.get(position));
	}
}
