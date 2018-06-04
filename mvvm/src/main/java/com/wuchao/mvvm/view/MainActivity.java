package com.wuchao.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.wuchao.mvvm.R;
import com.wuchao.mvvm.adapter.RepoListAdapter;
import com.wuchao.mvvm.databinding.ActivityMainBinding;
import com.wuchao.mvvm.model.Repo;
import com.wuchao.mvvm.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewModel.DataListener {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new MainViewModel(this);
        binding.setViewModel(mainViewModel);
        setSupportActionBar(binding.toolbar);
        binding.textDescription.setText(getResources().getString(R.string.github_java));
    }

    @Override
    public void repoDataChange(List<Repo> repos) {
        RepoListAdapter adapter = new RepoListAdapter(this, repos);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
    }
}
