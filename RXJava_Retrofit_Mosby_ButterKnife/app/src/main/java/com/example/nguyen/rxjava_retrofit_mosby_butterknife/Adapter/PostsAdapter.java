package com.example.nguyen.rxjava_retrofit_mosby_butterknife.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyen.rxjava_retrofit_mosby_butterknife.Model.Post;
import com.example.nguyen.rxjava_retrofit_mosby_butterknife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Nguyen on 10/8/2015.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private ArrayList<Post> posts;
    private Context context;

    public PostsAdapter(Context context, ArrayList<Post> posts) {
        this.context = context;
        this.posts = posts;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_post_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.textViewPostTitle.setText(posts.get(position).getTitle());
        holder.textViewPostBody.setText(posts.get(position).getBody());
        holder.textViewBtnReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "AA", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        if (posts != null)
            return posts.size();
        else
            return 0;
    }

    public void clear() {
        posts.clear();
    }

    public void addAll(List<Post> posts) {
        this.posts.addAll(posts);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.textViewPostTitle)
        TextView textViewPostTitle;
        @InjectView(R.id.textViewPostBody)
        TextView textViewPostBody;
        @InjectView(R.id.textViewBtnReadMore)
        TextView textViewBtnReadMore;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.inject(this, v);
        }
    }

}
