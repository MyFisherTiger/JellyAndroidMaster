package com.kjs.fishertiger.jelly_android_master.mvp.ui.wechat.activity;

import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;

import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.adapter.CollectionFragmentAdapter;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jelly_android_master.mvp.ui.wechat.fragment.FragmentWeChatFeaturedCommonClass;
import com.kjs.fishertiger.jelly_android_master.mvp.ui.wechat.fragment.FragmentWeChatFeaturedNoCommonClass;
import com.kjs.fishertiger.jelly_android_master.widget.CollectionViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class WeChatFeaturedActivity extends BaseActivity {

	@BindView(R.id.tabLayout)
	TabLayout tabLayout;
	@BindView(R.id.collectionVp)
	CollectionViewPager collectionVp;

	private CollectionFragmentAdapter collectionFragmentAdapter;

	private List<Fragment> fragments = new ArrayList<>();

	private int[] titleIds = new int[]{R.string.use_common_result,
			R.string.no_use_common_result};

	private FragmentWeChatFeaturedCommonClass fragmentWeChatFeaturedCommonClass;
	private FragmentWeChatFeaturedNoCommonClass fragmentWeChatFeaturedNoCommonClass;


	@Override
	public int getLayoutId() {
		return R.layout.activity_refresh;
	}

	@Override
	public void init() {

		setTitleContent(getString(R.string.activity_wechat_title));
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
		fragmentWeChatFeaturedCommonClass=new FragmentWeChatFeaturedCommonClass();
		fragmentWeChatFeaturedNoCommonClass=new FragmentWeChatFeaturedNoCommonClass();
		fragments.add(fragmentWeChatFeaturedCommonClass);
		fragments.add(fragmentWeChatFeaturedNoCommonClass);
	}

	@Override
	public void requestData() {
	}
}
