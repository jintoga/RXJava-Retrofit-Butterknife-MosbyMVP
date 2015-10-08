package com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Adapter.PostsAdapter;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Model.Post;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Presenter.LoadPostsPresenter;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.R;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Service.ForumService;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.Interfaces.LoadPostsView;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.ViewState.LoadPostsViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nguyen on 10/7/2015.
 */
public class FragmentShowPosts extends MvpViewStateFragment<LoadPostsView, LoadPostsPresenter> implements LoadPostsView {
    @InjectView(R.id.swipeRefreshLayoutListPosts)
    SwipeRefreshLayout swipeRefreshLayoutPosts;
    @InjectView(R.id.recyclerViewPosts)
    RecyclerView recyclerViewPosts;
    @InjectView(R.id.textViewError)
    TextView textViewError;
    @InjectView(R.id.progressBarLoadingPost)
    ProgressBar progressBarLoadingPost;

    PostsAdapter postsAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_posts, container, false);
        ButterKnife.inject(this, view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewPosts.setLayoutManager(layoutManager);
        ArrayList<Post> dummy = new ArrayList<Post>();
        postsAdapter = new PostsAdapter(getActivity(), dummy);
        recyclerViewPosts.setAdapter(postsAdapter);
        progressBarLoadingPost.setVisibility(View.VISIBLE);

        swipeRefreshLayoutPosts.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);
        swipeRefreshLayoutPosts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                postsAdapter.clear();
                postsAdapter.notifyDataSetChanged();
                onNewViewStateInstance();
            }
        });
        return view;
    }

    @Override
    public void addContentToRecyclerView(List<Post> posts) {
        postsAdapter.clear();
        postsAdapter.addAll(posts);
        postsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNewViewStateInstance() {
        getPresenter().doLoadPosts();
        loading();
    }

    @Override
    public void loading() {
        LoadPostsViewState loadPostsViewState = (LoadPostsViewState) viewState;
        loadPostsViewState.setLoading();

        swipeRefreshLayoutPosts.setRefreshing(true);
        textViewError.setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        LoadPostsViewState loadPostsViewState = (LoadPostsViewState) viewState;
        loadPostsViewState.setShowError();
        swipeRefreshLayoutPosts.setRefreshing(false);
        progressBarLoadingPost.setVisibility(View.GONE);

        textViewError.setVisibility(View.VISIBLE);
    }


    @Override
    public void loadSuccessful() {
        LoadPostsViewState loadPostsViewState = (LoadPostsViewState) viewState;
        loadPostsViewState.setShowSuccess();
        textViewError.setVisibility(View.GONE);
        progressBarLoadingPost.setVisibility(View.GONE);
        swipeRefreshLayoutPosts.setRefreshing(false);
        Toast.makeText(getActivity(), "All Posts Are Loaded!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public ViewState createViewState() {
        return new LoadPostsViewState();
    }

    @Override
    public LoadPostsPresenter createPresenter() {
        return new LoadPostsPresenter(this, new ForumService());
    }
}
