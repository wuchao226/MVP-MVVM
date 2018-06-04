package com.wuchao.mvvm.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wuchao.mvvm.R;
import com.wuchao.mvvm.databinding.ItemRepoBinding;
import com.wuchao.mvvm.model.Repository;
import com.wuchao.mvvm.viewmodel.ItemRepositoryViewModel;

import java.util.List;


/**
 * Created by xzhang
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private Context mContext;
    private List<Repository> repositoryList;

    public RepositoryAdapter(Context context, List<Repository> repositoryList) {
        this.mContext = context;
        this.repositoryList = repositoryList;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRepoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_repo, parent, false);
        //View view = LayoutInflater.from(mContext).inflate(R.layout.item_repo, parent, false);
        return new RepositoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = repositoryList.get(position);
        holder.bindData(repository);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        ItemRepoBinding mBinding;

        public RepositoryViewHolder(ItemRepoBinding binding) {
            super(binding.cardView);
            mBinding = binding;
        }

        public void bindData(Repository repository) {
            if (mBinding.getViewModel() == null) {
                mBinding.setViewModel(new ItemRepositoryViewModel(mContext, repository));
            } else {
                mBinding.getViewModel().setRepository(repository);
            }
        }


    }
}
