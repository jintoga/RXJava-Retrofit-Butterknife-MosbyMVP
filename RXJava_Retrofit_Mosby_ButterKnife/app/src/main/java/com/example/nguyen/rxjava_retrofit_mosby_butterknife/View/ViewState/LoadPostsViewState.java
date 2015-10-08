package com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.ViewState;

import com.example.nguyen.rxjava_retrofit_mosby_butterknife.View.Interfaces.LoadPostsView;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by Nguyen on 10/8/2015.
 */
public class LoadPostsViewState implements ViewState<LoadPostsView> {

    final int STATE_SHOW_LOADING = 0;
    final int STATE_SHOW_SUCCESS = 1;
    final int STATE_SHOW_ERROR = 2;

    int state = STATE_SHOW_LOADING;

    public void setLoading() {
        state = STATE_SHOW_LOADING;
    }

    public void setShowSuccess() {
        state = STATE_SHOW_SUCCESS;
    }

    public void setShowError() {
        state = STATE_SHOW_ERROR;
    }


    @Override
    public void apply(LoadPostsView loadPostsView, boolean b) {
        switch (state) {
            case STATE_SHOW_LOADING:
                loadPostsView.loading();
                break;

            case STATE_SHOW_ERROR:
                loadPostsView.showError();
                break;

            case STATE_SHOW_SUCCESS:
                loadPostsView.loadSuccessful();
                break;
        }
    }
}
