package com.wangguobao.myretrofit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by v-wangguobao-sy on 2016/9/28.
 */
public interface Service {
    @GET("/")
    Call<String> getBaidu();
}
