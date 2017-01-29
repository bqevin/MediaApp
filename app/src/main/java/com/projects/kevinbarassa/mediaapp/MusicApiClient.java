package com.projects.kevinbarassa.mediaapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin Barassa
 */

public class MusicApiClient {

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(AppConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static final MusicAPInterface SERVICE = RETROFIT.create(MusicAPInterface.class);

    public static MusicAPInterface getService() {
        return SERVICE;
    }
}
