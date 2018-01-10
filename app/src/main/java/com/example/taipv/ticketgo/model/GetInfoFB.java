package com.example.taipv.ticketgo.model;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/8/2018
 * Email: tai97nd@gmail.com
 */

public class


GetInfoFB {
    private long facebook_id;
    private String email;
    private String name;
    private String image;

    public GetInfoFB() {

    }

    public GetInfoFB(long facebook_id, String email, String name, String image) {
        this.facebook_id = facebook_id;
        this.email = email;
        this.name = name;
        this.image = image;
    }

    public long getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(long facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
