package com.wuchao.mvp.presenter.impl;

import com.wuchao.mvp.presenter.BasePresenter;
import com.wuchao.mvp.view.BaseView;

/**
 * @author: wuchao
 * @date: 2017/8/5 14:38
 * @desciption:
 */

public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private T mPresenterView;

    @Override
    public void attachView(T view) {
        mPresenterView = view;
    }

    @Override
    public void detachView() {
        mPresenterView = null;
    }
}
