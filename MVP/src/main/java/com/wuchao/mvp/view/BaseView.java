package com.wuchao.mvp.view;

/**
 * @author: wuchao
 * @date: 2017/8/5 14:27
 * @desciption:
 */

public interface BaseView<T> {

    void showProgress();

    void showErrorMessage();

    void showRecyclerView(T t);
}
