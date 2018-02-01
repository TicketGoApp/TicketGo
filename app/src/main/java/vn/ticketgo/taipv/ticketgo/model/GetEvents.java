package vn.ticketgo.taipv.ticketgo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/10/2018
 * Email: tai97nd@gmail.com
 */

public class GetEvents {
    @SerializedName("data")
    List<GetEventHot> events;

    public GetEvents(List<GetEventHot> events) {
        this.events = events;
    }

    public GetEvents() {
    }

    public List<GetEventHot> getEvents() {
        return events;
    }

    public void setEvents(List<GetEventHot> events) {
        this.events = events;
    }

}
