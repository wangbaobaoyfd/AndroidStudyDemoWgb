package com.wangguobao.retrofit_03_listview;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by v-wangguobao-sy on 2016/9/28.
 */
public interface Service {
    @GET("/api/{cotegory01}/list")
    Call<TngouBean> getList(@Path("cotegory01") String cotegory02, @Query("id") int id, @Query("page") int page, @Query("rows") int rows);
}
