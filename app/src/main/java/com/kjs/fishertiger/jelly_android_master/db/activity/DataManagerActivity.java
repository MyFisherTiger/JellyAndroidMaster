package com.kjs.fishertiger.jelly_android_master.db.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.CollectionFragmentAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.db.fragment.FragmentRealm;
import com.kjs.fishertiger.jelly_android_master.db.fragment.FragmentSharePreference;
import com.kjs.fishertiger.jelly_android_master.mvp.ui.wechat.fragment.FragmentWorldNews;
import com.kjs.fishertiger.jelly_android_master.widget.CollectionViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class DataManagerActivity extends BaseActivity {

	@BindView(R.id.tabLayout)
	TabLayout tabLayout;
	@BindView(R.id.collectionVp)
	CollectionViewPager collectionVp;

	private CollectionFragmentAdapter collectionFragmentAdapter;

	private List<Fragment> fragments = new ArrayList<>();

	private int[] titleIds = new int[]{R.string.retrofit_manager,
			R.string.sharePreference_manager,R.string.realm_manager};

	private FragmentWorldNews fragmentWorldNews;
	private FragmentSharePreference fragmentSharePreferencep;
	private FragmentRealm fragmentRealm;



	@Override
	public int getLayoutId() {
		return R.layout.activity_data_manager;
	}

	@Override
	public void init() {
		setTitleContent(getString(R.string.data_manager_title));
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
		fragmentWorldNews=new FragmentWorldNews();
		fragmentSharePreferencep=new FragmentSharePreference();
		fragmentRealm=new FragmentRealm();
		fragments.add(fragmentWorldNews);
		fragments.add(fragmentSharePreferencep);
		fragments.add(fragmentRealm);
	}

	@Override
	public void requestData() {

	}
}
