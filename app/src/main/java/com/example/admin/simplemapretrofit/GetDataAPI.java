package com.example.admin.simplemapretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataAPI {
    @GET("api/json/get/bPvorlFkwO")
    Call<List<Point>> listData(@Query("indent") short i);
}
