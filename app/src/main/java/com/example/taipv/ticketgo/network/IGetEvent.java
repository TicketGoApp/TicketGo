package com.example.taipv.ticketgo.network;

        import com.example.taipv.ticketgo.model.GetEvent;
        import com.example.taipv.ticketgo.model.GetEvents;

        import retrofit2.Call;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

/**
 * Created by taipv on 1/3/2018.
 */

public interface IGetEvent {
    @GET("api/vi/events")
    Call<GetEvent>getEvent(@Query("page")int page);
    @GET("api/vi/events_hot")
    Call<GetEvent>getEventHot(@Query("page")int page);

}
