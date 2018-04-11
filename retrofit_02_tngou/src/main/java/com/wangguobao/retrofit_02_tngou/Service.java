package com.wangguobao.retrofit_02_tngou;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by v-wangguobao-sy on 2016/9/28.
 */
public interface Service {
    @GET("/api/cook/list")
    Call<TngouBean> getList(@Query("id") int id, @Query("page") int page, @Query("rows") int rows);
}
