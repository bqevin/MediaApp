package com.projects.kevinbarassa.mediaapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Kevin Barassa on 29-Jan-17.
 */

public interface MusicAPInterface {
    @GET("/tracks?client_id=" + AppConfig.CLIENT_ID)
    Call<List<Music>> getRecentTracks(@Query("created_at") String date);
}
