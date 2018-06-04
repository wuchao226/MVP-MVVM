package com.wuchao.mvp.presenter;

import com.wuchao.mvp.view.UserRepoBaseView;

/**
 * @author: wuchao
 * @date: 2017/8/6 19:21
 * @desciption:
 */

public interface UserRepoPresenter extends BasePresenter<UserRepoBaseView> {

    void loadGitHubUserRepo(String userName);
}
