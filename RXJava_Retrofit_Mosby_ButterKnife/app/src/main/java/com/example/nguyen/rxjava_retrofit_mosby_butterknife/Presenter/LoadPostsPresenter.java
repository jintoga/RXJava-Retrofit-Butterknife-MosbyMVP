package com.example.nguyen.rxjava_retrofit_mosby_butterknife.Presenter;

import android.support.v4.app.Fragment;

import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Model.Post;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Service.ForumService;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.Interfaces.LoadPostsView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nguyen on 10/7/2015.
 */
public class LoadPostsPresenter extends MvpBasePresenter<LoadPostsView> {
    private Fragment fragment;
    private ForumService forumService;

    public LoadPostsPresenter(Fragment fragment, ForumService forumService) {
        this.fragment = fragment;
        this.forumService = forumService;
    }

    public void doLoadPosts() {
        if (isViewAttached()) {
            forumService.getApi()
                    .getPosts()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Post>>() {
                        @Override
                        public void onCompleted() {
                            if (isViewAttached()) {
                                getView().loadSuccessful();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if (isViewAttached()) {
                                getView().showError();
                            }
                        }

                        @Override
                        public void onNext(List<Post> posts) {
                            if (isViewAttached()) {
                                getView().addContentToRecyclerView(posts);
                            }
                        }
                    });
        }
    }
}
