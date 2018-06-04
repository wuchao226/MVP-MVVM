package com.wuchao.mvvm.viewmodel;

import android.databinding.ObservableInt;
import android.view.View;

import com.wuchao.mvvm.model.GithubService;
import com.wuchao.mvvm.model.Repo;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2017/8/6 21:07
 * @desciption:
 */

public class MainViewModel {

    public ObservableInt progressVisibility = new ObservableInt(View.GONE);
    public ObservableInt errorMessageVisibility = new ObservableInt(View.GONE);
    public ObservableInt recyclerViewVisibility = new ObservableInt(View.GONE);

    private List<Repo> mRepoList;
    private DataListener mListener;

    public MainViewModel(DataListener listener) {
        this.mListener = listener;
        loadGithubJava();
    }

    private void loadGithubJava() {
        progressVisibility.set(View.VISIBLE);
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
                        progressVisibility.set(View.GONE);
                        errorMessageVisibility.set(View.VISIBLE);
                        recyclerViewVisibility.set(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        progressVisibility.set(View.GONE);
                        errorMessageVisibility.set(View.GONE);
                        recyclerViewVisibility.set(View.VISIBLE);
                        if (mListener != null && mRepoList != null) {
                            mListener.repoDataChange(mRepoList);
                        }
                    }
                });
    }

    public interface DataListener {
        void repoDataChange(List<Repo> repos);
    }
}
