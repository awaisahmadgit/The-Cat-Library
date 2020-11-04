package com.gini.cattest.retrofit;

import com.gini.cattest.model.CatResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("v1/images/search")
    Call<List<CatResult>> getCatImages(@Query("size") String size,
                                                    @Query("order") String order,
                                                    @Query("limit") int limit,
                                                    @Query("page") int page);
}
