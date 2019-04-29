package com.kjs.fishertiger.jelly_android_master.activity.recyclerview;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.CollectionFragmentAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.recyclerview.FragmentDefaultRefreshAndLoading;
import com.kjs.fishertiger.jelly_android_master.recyclerview.FragmentDefinitionRefreshAndLoading;
import com.kjs.fishertiger.jelly_android_master.recyclerview.FragmentGoogleRefreshAndLoading;
import com.kjs.fishertiger.jelly_android_master.widget.CollectionViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class RefreshActivity extends BaseActivity {

	@BindView(R.id.tabLayout)
	TabLayout tabLayout;
	@BindView(R.id.collectionVp)
	CollectionViewPager collectionVp;

	private CollectionFragmentAdapter collectionFragmentAdapter;

	private FragmentDefaultRefreshAndLoading fragmentDefaultRefresh;
	private FragmentDefinitionRefreshAndLoading fragmentDefinitionRefresh;
	private FragmentGoogleRefreshAndLoading fragmentGoogleRefresh;

	private List<Fragment> fragments = new ArrayList<>();

	private int[] titleIds = new int[]{R.string.default_refresh,
			R.string.definition_refresh, R.string.google_refresh};

	@Override
	public int getLayoutId() {
		return R.layout.activity_refresh;
	}

	@Override
	public void init() {

		setTitleContent(getString(R.string.fragment_refresh_title));
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
		fragmentDefaultRefresh = new FragmentDefaultRefreshAndLoading();
		fragmentDefinitionRefresh = new FragmentDefinitionRefreshAndLoading();
		fragmentGoogleRefresh = new FragmentGoogleRefreshAndLoading();
		fragments.add(fragmentDefaultRefresh);
		fragments.add(fragmentDefinitionRefresh);
		fragments.add(fragmentGoogleRefresh);
	}

	@Override
	public void requestData() {

	}

}
