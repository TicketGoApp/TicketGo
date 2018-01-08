package com.example.taipv.ticketgo.network;

import com.example.taipv.ticketgo.model.TicketQR;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by taipv on 12/27/2017.
 */

public interface IGetTicketAPI {
    @GET("barcodes/search.php")
    Call<TicketQR>getTicket(@Query("code")String code);
}
