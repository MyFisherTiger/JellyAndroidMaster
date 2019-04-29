package com.kjs.fishertiger.jelly_android_master.activity.base.activity;
import android.widget.ImageView;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jelly_android_master.base.BaseActivity;
import com.kjs.fishertiger.jellylibrary.utils.DisplayUtils;
import com.kjs.fishertiger.jellylibrary.utils.GlideUtils;

import butterknife.BindView;


public class TransparentStatusBarActivity extends BaseActivity {

	@BindView(R.id.imgBg)
	ImageView imgBg;
	@BindView(R.id.ivImage)
	ImageView ivImage;
	@Override
	public int getLayoutId() {
		return R.layout.activity_transparent_statusbar;
	}

	@Override
	public void init() {

		DisplayUtils.setStatusBarFullTranslucent(this);

		GlideUtils.loadImg(this,
				"https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=06f0367c57b5c9ea76fe0bb1b450dd65/d1a20cf431adcbef44627e71a0af2edda3cc9f76.jpg",
				R.mipmap.ic_bttom_loading_01,ivImage);

		GlideUtils.loadImgBlur(this,
				"https://gss2.bdstatic.com/9fo3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike272%2C5%2C5%2C272%2C90/sign=06f0367c57b5c9ea76fe0bb1b450dd65/d1a20cf431adcbef44627e71a0af2edda3cc9f76.jpg",
				R.mipmap.ic_bttom_loading_01,imgBg);
	}

	@Override
	public void requestData() {

	}
}
