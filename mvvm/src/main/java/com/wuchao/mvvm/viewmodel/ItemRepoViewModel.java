package com.wuchao.mvvm.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.ObservableInt;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.wuchao.mvvm.R;
import com.wuchao.mvvm.model.Repo;
import com.wuchao.mvvm.util.FavoReposHelper;
import com.wuchao.mvvm.view.UserRepoActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author: wuchao
 * @date: 2017/8/6 21:28
 * @desciption:
 */

public class ItemRepoViewModel {

    public ObservableInt favStarImage = new ObservableInt();
    private Repo repo;
    private static Context sContext;

    public ItemRepoViewModel(Context context, Repo repo) {
        this.repo = repo;
        this.sContext = context;
        if (FavoReposHelper.getInstance().contains(repo)) {
            favStarImage.set(R.drawable.ic_star_checked);
        } else {
            favStarImage.set(R.drawable.ic_star_unchecked);
        }
    }

    private void openUserRepoActivity(String name){
        Intent intent = new Intent(sContext, UserRepoActivity.class) ;
        intent.putExtra("username",name);
        sContext.startActivity(intent);
    }

    public void onItemClick(View view) {
        openUserRepoActivity(repo.getOwner());
    }

    public void onImageClick1(View view) {
        openUserRepoActivity(repo.getContributors().get(0).getName());
    }

    public void onImageClick2(View view) {
        openUserRepoActivity(repo.getContributors().get(1).getName());
    }

    public void onImageClick3(View view) {
        openUserRepoActivity(repo.getContributors().get(2).getName());
    }

    public void onImageClick4(View view) {
        openUserRepoActivity(repo.getContributors().get(3).getName());
    }

    public void onImageClick5(View view) {
        openUserRepoActivity(repo.getContributors().get(4).getName());
    }

    public void onFavClick(View view) {
        if (FavoReposHelper.getInstance().contains(repo)) {
            favStarImage.set(R.drawable.ic_star_unchecked);
            FavoReposHelper.getInstance().removeFavo(repo);
        } else {
            favStarImage.set(R.drawable.ic_star_checked);
            FavoReposHelper.getInstance().addFavo(repo);
        }
    }

    @BindingAdapter({"avatarUrl"})
    public static void setAvatarImage(CircleImageView image, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(sContext).load(url).into(image);
        }
    }

    @BindingAdapter({"favStar"})
    public static void setFavImageRes(ImageView imageRes,int id) {
        imageRes.setImageResource(id);
    }

    public int getFavImageId(){
        return favStarImage.get();
    }

    public String getTitle() {
        return repo.getOwner() + " / " + repo.getName();
    }

    public String getDes() {
        return repo.getDes();
    }

    public String getMeta() {
        return repo.getMeta();
    }

    public String getAvatar1() {
        if (repo.getContributors().size() > 0) {
            return repo.getContributors().get(0).getAvatar();
        }
        return null;
    }

    public String getAvatar2() {
        if (repo.getContributors().size() > 1) {
            return repo.getContributors().get(1).getAvatar();
        }
        return null;
    }

    public String getAvatar3() {
        if (repo.getContributors().size() > 2) {
            return repo.getContributors().get(2).getAvatar();
        }
        return null;
    }

    public String getAvatar4() {
        if (repo.getContributors().size() > 3) {
            return repo.getContributors().get(3).getAvatar();
        }
        return null;
    }

    public String getAvatar5() {
        if (repo.getContributors().size() > 4) {
            return repo.getContributors().get(4).getAvatar();
        }
        return null;
    }

    public void setRepo(Repo repo) {
        this.repo = repo;
    }
}
