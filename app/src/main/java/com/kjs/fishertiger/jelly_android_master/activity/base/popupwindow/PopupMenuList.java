package com.kjs.fishertiger.jelly_android_master.activity.base.popupwindow;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.activity.base.adapter.PopupMenuListAdapter;
import com.kjs.fishertiger.jellylibrary.base.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PopupMenuList extends BasePopupWindow {

	@BindView(R.id.rv)
	RecyclerView recyclerView;


	public PopupMenuList(Context context) {
		super(context, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

		ButterKnife.bind(this,popupView);
		List<String> list=new ArrayList<>();
		for(int i=0;i<5;i++){
			list.add("item"+i);
		}

		PopupMenuListAdapter popupMenuListAdapter=new PopupMenuListAdapter(context,list);
		LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(linearLayoutManager);
		recyclerView.setAdapter(popupMenuListAdapter);
	}

	@Override
	public int getPopupLayoutRes() {
		return R.layout.popup_menu_list;
	}

	@Override
	public int getPopupAnimationStyleRes() {
		return 0;
	}
}
