package vn.ticketgo.taipv.ticketgo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Author: Phùng Tài NeverGiveUp
 * Date: 1/15/2018
 * Email: tai97nd@gmail.com
 */

public class GetEvent {
    @SerializedName("events")
    private GetEvents getEvents;
    @SerializedName("events_hot")
    private GetEvents getEventsHot;
    public GetEvents getGetEvents() {
        return getEvents;
    }

    public void setGetEvents(GetEvents getEvents) {
        this.getEvents = getEvents;
    }

    public GetEvents getGetEventsHot() {
        return getEventsHot;
    }

    public void setGetEventsHot(GetEvents getEventsHot) {
        this.getEventsHot = getEventsHot;
    }
}
