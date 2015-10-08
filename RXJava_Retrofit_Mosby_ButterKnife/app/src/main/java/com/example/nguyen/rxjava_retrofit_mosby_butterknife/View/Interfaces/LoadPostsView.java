package com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.Interfaces;

import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Model.Post;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by Nguyen on 10/7/2015.
 */
public interface LoadPostsView extends MvpView {
    void loading();

    void addContentToRecyclerView(List<Post> posts);

    void loadSuccessful();

    void showError();
}
