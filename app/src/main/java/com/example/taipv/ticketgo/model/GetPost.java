package com.example.taipv.ticketgo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/19/2018
 * Email: tai97nd@gmail.com
 */

public class GetPost {
    @SerializedName("posts")
    private GetPosts getPosts;

    public GetPosts getGetPosts() {
        return getPosts;
    }

    public void setGetPosts(GetPosts getPosts) {
        this.getPosts = getPosts;
    }
}
