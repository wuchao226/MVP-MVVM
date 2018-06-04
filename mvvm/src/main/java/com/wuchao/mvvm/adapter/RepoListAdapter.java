package com.wuchao.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wuchao.mvvm.R;
import com.wuchao.mvvm.databinding.RepoListItemBinding;
import com.wuchao.mvvm.model.Repo;
import com.wuchao.mvvm.viewmodel.ItemRepoViewModel;

import java.util.List;

/**
 * Created by xzhang .
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {

    private Context mContext;

    private List<Repo> repoList;

    public RepoListAdapter(Context context, List<Repo> repoList) {
        this.mContext = context;
        this.repoList = repoList;
    }

    public void setRepoList(List<Repo> repoList) {
        this.repoList = repoList;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RepoListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.repo_list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Repo repo = repoList.get(position);
        holder.bindData(repo);
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RepoListItemBinding mBinding;

        public ViewHolder(RepoListItemBinding binding) {
            super(binding.repoCard);
            mBinding = binding;
        }

        public void bindData(Repo repo) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemRepoViewModel(mContext, repo));
            } else {
                mBinding.getViewModel().setRepo(repo);
            }
        }
    }
}
