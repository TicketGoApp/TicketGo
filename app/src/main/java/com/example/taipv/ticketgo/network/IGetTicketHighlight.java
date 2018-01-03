package com.example.taipv.ticketgo.network;

import com.example.taipv.ticketgo.model.GetTicketHighlight;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by taipv on 1/3/2018.
 */

public interface IGetTicketHighlight {
    @GET("json/movies_2017.json")
    Call<GetTicketHighlight>getTicket();
}
