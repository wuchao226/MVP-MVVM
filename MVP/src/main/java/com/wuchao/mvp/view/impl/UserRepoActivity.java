package com.wuchao.mvp.view.impl;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuchao.mvp.adapter.RepositoryAdapter;
import com.wuchao.mvp.model.Repository;
import com.wuchao.mvp.presenter.impl.UserRepoPresenterImpl;
import com.wuchao.mvp.view.UserRepoBaseView;
import com.wuchao.mvp1.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserRepoActivity extends AppCompatActivity implements UserRepoBaseView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.button_search)
    ImageButton mButtonSearch;
    @BindView(R.id.edit_text_username)
    EditText mEditTextUsername;
    @BindView(R.id.layout_search)
    RelativeLayout mLayoutSearch;
    @BindView(R.id.layout_header)
    LinearLayout mLayoutHeader;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.text_info)
    TextView mTextInfo;
    @BindView(R.id.repos_recycler_view)
    RecyclerView mReposRecyclerView;
    private UserRepoPresenterImpl mPresenter;
    private RepositoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo);
        ButterKnife.bind(this);

        String username = getIntent().getStringExtra("username");
        mEditTextUsername.setText(username);
        addTextListener();
        setSupportActionBar(mToolbar);
        mPresenter = new UserRepoPresenterImpl();
        mPresenter.attachView(this);

        mPresenter.loadGitHubUserRepo(username);
    }

    private void addTextListener() {
        mEditTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mButtonSearch.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditTextUsername.getWindowToken(), 0);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
        mTextInfo.setVisibility(View.GONE);
        mReposRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        mProgress.setVisibility(View.GONE);
        mTextInfo.setVisibility(View.VISIBLE);
        mReposRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(List<Repository> repositories) {
        mProgress.setVisibility(View.GONE);
        mTextInfo.setVisibility(View.GONE);
        mReposRecyclerView.setVisibility(View.VISIBLE);

        adapter = new RepositoryAdapter(this, repositories);
        mReposRecyclerView.setAdapter(adapter);
        mReposRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hideSoftKeyboard();
    }

    @OnClick(R.id.button_search)
    public void onViewClicked() {
        mPresenter.loadGitHubUserRepo(mEditTextUsername.getText().toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
