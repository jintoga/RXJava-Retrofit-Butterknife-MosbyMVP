package com.example.nguyen.rxjava_retrofit_mosby_butterknife.Model;

/**
 * Created by Nguyen on 10/6/2015.
 */
public class Post {
    public int userId;
    public int id;
    public String title;
    public String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
