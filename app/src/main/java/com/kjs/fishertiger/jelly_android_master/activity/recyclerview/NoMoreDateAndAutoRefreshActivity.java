package com.kjs.fishertiger.jelly_android_master.activity.recyclerview;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.CollectionFragmentAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.recyclerview.FragmentAutoRefresh;
import com.kjs.fishertiger.jelly_android_master.recyclerview.FragmentNoMoreData;
import com.kjs.fishertiger.jelly_android_master.widget.CollectionViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class NoMoreDateAndAutoRefreshActivity extends BaseActivity {

	@BindView(R.id.tabLayout)
	TabLayout tabLayout;
	@BindView(R.id.collectionVp)
	CollectionViewPager collectionVp;

	private CollectionFragmentAdapter collectionFragmentAdapter;
	private List<Fragment> fragments = new ArrayList<>();

	private int[] titleIds = new int[]{R.string.no_more_data,
			R.string.auto_refresh};

	private FragmentNoMoreData fragmentNoMoreData;
	private FragmentAutoRefresh fragmentAutoRefresh;

	@Override
	public int getLayoutId() {
		return R.layout.activity_refresh;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.fragment_auto_title));
		showHomeAsUp(R.mipmap.ic_back_btn);

		setFragments();
		collectionFragmentAdapter = new CollectionFragmentAdapter(getSupportFragmentManager(), fragments);
		collectionVp.setAdapter(collectionFragmentAdapter);
		tabLayout.setupWithViewPager(collectionVp);
		collectionVp.setOffscreenPageLimit(fragments.size());

		for (int i = 0; i < tabLayout.getTabCount(); i++) {
			tabLayout.getTabAt(i).setText(titleIds[i]);
		}
	}

	private void setFragments() {
		fragmentNoMoreData=new FragmentNoMoreData();
		fragmentAutoRefresh=new FragmentAutoRefresh();
		fragments.add(fragmentNoMoreData);
		fragments.add(fragmentAutoRefresh);
	}


	@Override
	public void requestData() {

	}
}
