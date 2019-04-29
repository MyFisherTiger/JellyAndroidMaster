package com.kjs.fishertiger.jelly_android_master.base;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.kjs.fishertiger.jelly_android_master.R;
import com.kjs.fishertiger.jellylibrary.base.IBaseActivity;
import com.kjs.fishertiger.jellylibrary.mvp.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<T extends BasePresenter> extends IBaseActivity {
    private Unbinder unbinder;
    public Toolbar mCommonToolbar;
    public TextView titleTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder= ButterKnife.bind(this);

        mCommonToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        titleTv= ButterKnife.findById(this, R.id.titleTv);
        if(mCommonToolbar!=null){
            setupToolbar(mCommonToolbar);
            setTitle("");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    /**
     * 设置Toolbar成ActionBar
     */
    protected void setupToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    /** ActionBar显示返回图标 */
    protected void showHomeAsUp(@DrawableRes int resId) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(resId);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 自定义Toolbar的title内容
     */
    protected void setTitleContent(String title) {
        if (titleTv != null) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
