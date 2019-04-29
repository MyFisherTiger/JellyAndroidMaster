package com.kjs.fishertiger.jelly_android_master.activity.recyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.CollectionFragmentAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.recyclerview.FragmentAddHeader;
import com.kjs.fishertiger.jelly_android_master.recyclerview.FragmentEmptyView;
import com.kjs.fishertiger.jelly_android_master.widget.CollectionViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class HeaderAndEmptyViewActivity extends BaseActivity {

	@BindView(R.id.tabLayout)
	TabLayout tabLayout;
	@BindView(R.id.collectionVp)
	CollectionViewPager collectionVp;

	private CollectionFragmentAdapter collectionFragmentAdapter;
	private List<Fragment> fragments = new ArrayList<>();

	private int[] titleIds = new int[]{R.string.add_header,
			R.string.set_empty};

	private FragmentAddHeader fragmentAddHeader;
	private FragmentEmptyView fragmentEmptyView;

	@Override
	public int getLayoutId() {
		return R.layout.activity_refresh;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.fragment_header_title));
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
		fragmentAddHeader = new FragmentAddHeader();
		fragmentEmptyView = new FragmentEmptyView();
		fragments.add(fragmentAddHeader);
		fragments.add(fragmentEmptyView);
	}

	@Override
	public void requestData() {

	}

	public void refreshUI() {

	}
}
