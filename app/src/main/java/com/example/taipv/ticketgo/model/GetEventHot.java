package com.example.taipv.ticketgo.model;

/**
 * Created by taipv on 1/3/2018.
 */

public class GetEventHot {

    private String name;
    private String logo;
    private String event_local_name;
    private String address;

    public GetEventHot(String name, String logo, String event_local_name, String address) {
        this.name = name;
        this.logo = logo;
        this.event_local_name = event_local_name;
        this.address = address;
    }

    public GetEventHot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEvent_local_name() {
        return event_local_name;
    }

    public void setEvent_local_name(String event_local_name) {
        this.event_local_name = event_local_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
