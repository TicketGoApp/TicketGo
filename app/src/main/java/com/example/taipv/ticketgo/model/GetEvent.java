package com.example.taipv.ticketgo.model;

import com.example.taipv.ticketgo.model.server.GetAllEvent;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/10/2018
 * Email: tai97nd@gmail.com
 */

public class GetEvent {
    @SerializedName("events_hot")
    List<GetEventHot> eventHot;
    @SerializedName("events")
    List<GetAllEvent> allEvent;

    public GetEvent() {
    }

    public GetEvent(List<GetEventHot> eventHot, List<GetAllEvent> allEvent) {
        this.eventHot = eventHot;
        this.allEvent = allEvent;
    }

    public List<GetEventHot> getEventHot() {
        return eventHot;
    }

    public void setEventHot(List<GetEventHot> eventHot) {
        this.eventHot = eventHot;
    }

    public List<GetAllEvent> getAllEvent() {
        return allEvent;
    }

    public void setAllEvent(List<GetAllEvent> allEvent) {
        this.allEvent = allEvent;
    }
}
