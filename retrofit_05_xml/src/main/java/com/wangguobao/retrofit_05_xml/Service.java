package com.wangguobao.retrofit_05_xml;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by v-wangguobao-sy on 2016/9/29.
 */
public interface Service {
    @GET("/portal.php?mod=rss&catid=")
    Call<Channel> getChannel();
}
