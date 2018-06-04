package com.wuchao.mvvm.viewmodel;

import android.databinding.ObservableInt;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.wuchao.mvvm.model.GithubService;
import com.wuchao.mvvm.model.Repository;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: wuchao
 * @date: 2017/8/16 18:42
 * @desciption:
 */

public class RespositoryViewModel {

    public ObservableInt progressVisibility;
    public ObservableInt errorMessageVisibility;
    public ObservableInt searchButtonVisibility;
    public ObservableInt recyclerViewVisibility;
    public String editTextUserNameValue;

    private List<Repository> mRepositoryList;
    private DataListener mListener;

    public RespositoryViewModel(String userName, DataListener dataListener) {
        mListener = dataListener;
        searchButtonVisibility = new ObservableInt(View.VISIBLE);
        progressVisibility = new ObservableInt(View.VISIBLE);
        errorMessageVisibility = new ObservableInt(View.GONE);
        recyclerViewVisibility = new ObservableInt(View.GONE);
        loadGitHubUserRepo(userName);
    }

    public void setUserName(String userName, DataListener dataListener) {
        mListener = dataListener;
        loadGitHubUserRepo(userName);
    }

    //搜索按钮点击事件
    public void onClickSearch(View view) {
        loadGitHubUserRepo(editTextUserNameValue);
    }

    public TextWatcher usernameEditTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextUserNameValue = s.toString();
                searchButtonVisibility.set(s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void loadGitHubUserRepo(String userName) {
        if (TextUtils.isEmpty(userName)) {
            return;
        }
        progressVisibility.set(View.VISIBLE);
        errorMessageVisibility = new ObservableInt(View.GONE);
        recyclerViewVisibility = new ObservableInt(View.GONE);

        GithubService.Factory.create().publicRepositories(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {

                    @Override
                    public void onError(Throwable e) {
                        progressVisibility.set(View.GONE);
                        errorMessageVisibility.set(View.VISIBLE);
                        recyclerViewVisibility.set(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        progressVisibility.set(View.GONE);
                        errorMessageVisibility.set(View.GONE);
                        if (mListener != null ) {
                            mListener.repoDataChange(mRepositoryList);
                        }
                        if (mRepositoryList != null && mRepositoryList.size() > 0) {
                            recyclerViewVisibility.set(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        mRepositoryList = repositories;
                    }
                });
    }

    public interface DataListener {
        void repoDataChange(List<Repository> repositoryList);
    }
}
