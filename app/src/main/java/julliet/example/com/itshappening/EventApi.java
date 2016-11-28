package julliet.example.com.itshappening;

import retrofit.http.GET;
import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
/**
 * Created by julliet.nyaware on 11/16/2016.
 */


public interface EventApi {

    /*Retrofit get annotation with our URL
       And our method that will return us the list ob Book
    */
    @GET("/RetrofitExample/books.json")
    public void getEvent(Callback<List<Event>> response);
}