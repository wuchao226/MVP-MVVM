package com.wuchao.mvp.presenter.impl;

import com.wuchao.mvp.model.GithubService;
import com.wuchao.mvp.model.Repository;
import com.wuchao.mvp.presenter.UserRepoPresenter;
import com.wuchao.mvp.view.UserRepoBaseView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2017/8/6 19:22
 * @desciption:
 */

public class UserRepoPresenterImpl implements UserRepoPresenter {

    private List<Repository> repositoryList ;
    private UserRepoBaseView mBaseView;

    @Override
    public void attachView(UserRepoBaseView view) {
        mBaseView = view;
    }

    @Override
    public void detachView() {
        mBaseView = null;
    }

    @Override
    public void loadGitHubUserRepo(String userName) {
        mBaseView.showProgress();
        GithubService.Factory.create().publicRepositories(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {

                    @Override
                    public void onError(Throwable e) {
                        mBaseView.showErrorMessage();
                    }

                    @Override
                    public void onComplete() {
                        if(repositoryList != null){
                            mBaseView.showRecyclerView(repositoryList);
                        }
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        repositoryList = repositories ;
                    }
                });
    }
}
