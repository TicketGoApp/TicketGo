package com.example.taipv.ticketgo.model;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/5/2018
 * Email: tai97nd@gmail.com
 */

public class ProfileModel {
    private String titleProfile;
    private int iconProfile;

    public String getTitleProfile() {
        return titleProfile;
    }

    public void setTitleProfile(String titleProfile) {
        this.titleProfile = titleProfile;
    }

    public int getIconProfile() {
        return iconProfile;
    }

    public void setIconProfile(int iconProfile) {
        this.iconProfile = iconProfile;
    }

    public ProfileModel(String titleProfile, int iconProfile) {
        this.titleProfile = titleProfile;
        this.iconProfile = iconProfile;
    }
}
