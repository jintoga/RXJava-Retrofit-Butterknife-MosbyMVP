package com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.Interfaces;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Nguyen on 10/7/2015.
 */
public interface LoadPostsView extends MvpView {
    void showError();
    void loadSuccessful();
}
