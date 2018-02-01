package vn.ticketgo.taipv.ticketgo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/19/2018
 * Email: tai97nd@gmail.com
 */

public class GetPosts {
    @SerializedName("data")
   private List<PostNews>data;

    public List<PostNews> getData() {
        return data;
    }

    public void setData(List<PostNews> data) {
        this.data = data;
    }
}
