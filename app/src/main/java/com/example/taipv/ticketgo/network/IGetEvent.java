package com.example.taipv.ticketgo.network;

        import com.example.taipv.ticketgo.model.GetEvent;
        import com.example.taipv.ticketgo.model.GetEventHot;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.http.GET;

/**
 * Created by taipv on 1/3/2018.
 */

public interface IGetEvent {
    @GET("api/vi/home")
    Call<GetEvent>getEvent();
}
