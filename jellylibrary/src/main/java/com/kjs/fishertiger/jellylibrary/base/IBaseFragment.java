package com.kjs.fishertiger.jellylibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kjs.fishertiger.jellylibrary.mvp.BasePresenter;
import com.kjs.fishertiger.jellylibrary.mvp.ClassGetUtil;


public abstract class IBaseFragment<T extends BasePresenter> extends Fragment implements FragmentUserVisibleController.UserVisibleCallback{

    private boolean isInit; // 是否可以开始加载数据
    private boolean isCreated;

    private FragmentUserVisibleController userVisibleController;
    public View mainView;
    public T mPresenter;

    public IBaseFragment() {
        userVisibleController = new FragmentUserVisibleController(this, this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(getLayoutId(), container, false);
        mPresenter= ClassGetUtil.getClass(this,0);

        if(mPresenter!=null){
            mPresenter.setV(this);
        }

        return mainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isInit = true;
        userVisibleController.activityCreated();
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        userVisibleController.resume();
        if (getUserVisibleHint()) {
            if (isInit && isCreated) {
                isInit = false;// 加载数据完成
                //System.out.println("应该去加载数据了");
                requestData();
            }
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        userVisibleController.setUserVisibleHint(isVisibleToUser);
        // 每次切换fragment时调用的方法
        if (isVisibleToUser) {
            if (isInit&&isCreated) {
                isInit = false;//加载数据完成
                requestData();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        userVisibleController.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.onDestroy();
        }
    }

    @Override
    public void setWaitingShowToUser(boolean waitingShowToUser) {
        userVisibleController.setWaitingShowToUser(waitingShowToUser);
    }

    @Override
    public boolean isWaitingShowToUser() {
        return userVisibleController.isWaitingShowToUser();
    }

    @Override
    public boolean isVisibleToUser() {
        return userVisibleController.isVisibleToUser();
    }

    @Override
    public void callSuperSetUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onVisibleToUserChanged(boolean isVisibleToUser, boolean invokeInResumeOrPause) {

    }

    /**
     *  布局文件加载
     */
    public abstract int getLayoutId();

    /**
     * 初始化参数
     */
    public abstract void init();
    /**
     * 请求数据
     */
    public abstract void requestData();

}


