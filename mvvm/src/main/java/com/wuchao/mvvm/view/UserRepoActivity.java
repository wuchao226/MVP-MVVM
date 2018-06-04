package com.wuchao.mvvm.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.wuchao.mvvm.R;
import com.wuchao.mvvm.adapter.RepositoryAdapter;
import com.wuchao.mvvm.databinding.ActivityUserRepoBinding;
import com.wuchao.mvvm.model.Repository;
import com.wuchao.mvvm.viewmodel.RespositoryViewModel;

import java.util.List;


public class UserRepoActivity extends AppCompatActivity implements RespositoryViewModel.DataListener {

    private ActivityUserRepoBinding mUserRepoBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserRepoBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_repo);
        String username = getIntent().getStringExtra("username");
        RespositoryViewModel respositoryViewModel = new RespositoryViewModel(username, this);
        mUserRepoBinding.setViewModel(respositoryViewModel);
        mUserRepoBinding.editTextUsername.setText(username);
        //addTextListener();
        setSupportActionBar(mUserRepoBinding.toolbar);
       /* mUserRepoBinding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUserRepoBinding.editTextUsername.getText().toString();
                mUserRepoBinding.getViewModel().setUserName(name, UserRepoActivity.this);
            }
        });*/
    }

    private void addTextListener() {
        mUserRepoBinding.editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mUserRepoBinding.buttonSearch.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mUserRepoBinding.editTextUsername.getWindowToken(), 0);
    }

    @Override
    public void repoDataChange(List<Repository> repositoryList) {
        RepositoryAdapter adapter = new RepositoryAdapter(this, repositoryList);
        mUserRepoBinding.reposRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUserRepoBinding.reposRecyclerView.setAdapter(adapter);
        hideSoftKeyboard();
    }
}
