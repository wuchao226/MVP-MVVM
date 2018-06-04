package com.wuchao.mvp.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wuchao.mvp.adapter.RepoListAdapter;
import com.wuchao.mvp.presenter.impl.MainPresenterImpl;
import com.wuchao.mvp.view.MainBaseView;
import com.wuchao.mvp1.R;
import com.wuchao.mvp.model.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainBaseView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.text_description)
    TextView mTextDescription;
    @BindView(R.id.layout_header)
    LinearLayout mLayoutHeader;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.text_info)
    TextView mTextInfo;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    private MainPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mTextDescription.setText("GitHub Java");
        mMainPresenter = new MainPresenterImpl();
        mMainPresenter.attachView(this);
        mMainPresenter.loadGithubJava();
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
        mTextInfo.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        mProgress.setVisibility(View.GONE);
        mTextInfo.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(List<Repo> repos) {
        mProgress.setVisibility(View.GONE);
        mTextInfo.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);

        RepoListAdapter adapter = new RepoListAdapter(this,repos) ;
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }
}
