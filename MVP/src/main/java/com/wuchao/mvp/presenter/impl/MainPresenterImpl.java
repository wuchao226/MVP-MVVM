package com.wuchao.mvp.presenter.impl;

import com.wuchao.mvp.model.GithubService;
import com.wuchao.mvp.model.Repo;
import com.wuchao.mvp.presenter.MainPresenter;
import com.wuchao.mvp.view.MainBaseView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2017/8/5 14:41
 * @desciption:
 */

public class MainPresenterImpl implements MainPresenter {

    private MainBaseView mMainBaseView;
    private List<Repo> mRepoList;

    @Override
    public void attachView(MainBaseView view) {
        mMainBaseView = view;
    }

    @Override
    public void detachView() {
        mMainBaseView = null;
    }

    @Override
    public void loadGithubJava() {
        String url = "http://github.laowch.com/json/java_daily";
        GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Repo> repos) {
                        mRepoList = repos;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mMainBaseView.showErrorMessage();
                    }

                    @Override
                    public void onComplete() {
                        if (mRepoList != null) {
                            mMainBaseView.showRecyclerView(mRepoList);
                        }
                    }
                });
    }
}
