package com.wuchao.mvp.presenter;

import com.wuchao.mvp.view.BaseView;

/**
 * @author: wuchao
 * @date: 2017/8/5 14:27
 * @desciption: 业务逻辑处理基类
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);
    void detachView();
}
