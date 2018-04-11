package com.wangguobao.retrofit_04_post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by v-wangguobao-sy on 2016/9/28.
 */
public interface Service {
    /**
     * @POST
     * @FormUrlEncoded  //编码
     * @Field添加参数
     */
    @POST("/api/{cotegory01}/list")
    @FormUrlEncoded  //编码
    Call<TngouBean> getList(@Path("cotegory01") String cotegory02, @Field("id") int id, @Field("page") int page, @Field("rows") int rows);
}
