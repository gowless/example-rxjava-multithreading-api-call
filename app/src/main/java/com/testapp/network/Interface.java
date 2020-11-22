package com.testapp.network;

import com.testapp.model.Main;


import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {

    //query for repos
    @GET("search/repositories")
    Observable<Main> getData(
            @Query("q") String appId,
            @Query("per_page") String number,
            @Query("page") String page_number,
            @Query("sort") String sort

    );



}
