package com.example.taipv.ticketgo.model;

import java.util.List;

/**
 * Created by taipv on 1/3/2018.
 */

public class GetEventHot {
    private String start_date;
    private String end_date;
    private String information_name;
    private String name;
    private String logo;
    private String event_local_name;
    private String address;
    private String city_name;
    private String url_name;
    List<TicketName>tickets_name;

    public GetEventHot() {
    }

    public GetEventHot(String start_date, String end_date, String name, String logo, String event_local_name, String address, String city_name, String url_name, List<TicketName> tickets_name) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.name = name;
        this.logo = logo;
        this.event_local_name = event_local_name;
        this.address = address;
        this.city_name = city_name;
        this.url_name = url_name;
        this.tickets_name = tickets_name;
    }

    public String getInformation_name() {
        return information_name;
    }

    public void setInformation_name(String information_name) {
        this.information_name = information_name;
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

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getUrl_name() {
        return url_name;
    }

    public void setUrl_name(String url_name) {
        this.url_name = url_name;
    }

    public List<TicketName> getTickets_name() {
        return tickets_name;
    }

    public void setTickets_name(List<TicketName> tickets_name) {
        this.tickets_name = tickets_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}
