package com.example.ravi.listpractice;

import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Ravi on 08-12-2016.
 */

public interface Apiservice
{
    @GET("/bins/31b8v")
    public void apiDetails(Callback<JsonObject> callback);
}



