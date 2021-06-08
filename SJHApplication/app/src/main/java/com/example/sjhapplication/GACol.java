package com.example.sjhapplication;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GACol {
    // @GET( EndPoint-자원위치(URI) )
    @POST("/collect")
    Call<HashMap<String,String>> getPost(@FieldMap HashMap<String,String> pageMap);
}

