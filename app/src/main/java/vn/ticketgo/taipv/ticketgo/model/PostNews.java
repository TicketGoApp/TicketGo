package vn.ticketgo.taipv.ticketgo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/19/2018
 * Email: tai97nd@gmail.com
 */

public class PostNews {
    private String image;
    private String title;
    @SerializedName("category_name")
    private String category;
    @SerializedName("url_name")
    private String linkPost;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLinkPost() {
        return linkPost;
    }

    public void setLinkPost(String linkPost) {
        this.linkPost = linkPost;
    }
}
