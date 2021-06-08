package com.example.sjhapplication;

import java.util.HashMap;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitAPI {

    @GET("/users/list")
    Call<String> getMovieList();

//    @GET("/users/list")
//    Call<Result> getMovieList();

    @FormUrlEncoded
    @POST("/collect")
    Call<String>postFirst(@FieldMap HashMap<String, String> parameters);
//    Call<Response<GET>>postFirst(@FieldMap HashMap<String, String> parameters);
}
