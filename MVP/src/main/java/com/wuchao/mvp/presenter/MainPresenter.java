package com.wuchao.mvp.presenter;

import com.wuchao.mvp.view.MainBaseView;

/**
 * @author: wuchao
 * @date: 2017/8/5 14:36
 * @desciption: 处理MainActivity当中的业务逻辑
 */

public interface MainPresenter extends BasePresenter<MainBaseView>{

    void loadGithubJava();
}
