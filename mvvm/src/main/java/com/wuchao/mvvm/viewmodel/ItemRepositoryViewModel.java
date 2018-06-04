package com.wuchao.mvvm.viewmodel;

import android.content.Context;

import com.wuchao.mvvm.R;
import com.wuchao.mvvm.model.Repository;

/**
 * @author: wuchao
 * @date: 2017/8/18 19:03
 * @desciption:
 */

public class ItemRepositoryViewModel {

    private Repository mRepository;
    private Context mContext;

    public ItemRepositoryViewModel(Context context, Repository repository) {
        mContext = context;
        mRepository = repository;
    }

    public void setRepository(Repository repository) {
        mRepository = repository;
    }

    public String getTitle() {
        return mRepository.getName();
    }

    public String getDesc() {
        return mRepository.getDescription();
    }

    public String getWatcher() {
        return mContext.getResources().getString(R.string.text_watchers, mRepository.getWatchers());
    }

    public String getStar() {
        return mContext.getResources().getString(R.string.text_stars, mRepository.getStargazers_count());
    }

    public String getFork() {
        return mContext.getResources().getString(R.string.text_forks, mRepository.getForks());
    }
}
