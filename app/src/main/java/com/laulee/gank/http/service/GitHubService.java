package com.laulee.gank.http.service;


import com.laulee.gank.bean.GankEntity;
import com.laulee.gank.bean.GitHubUser;
import com.laulee.gank.bean.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by laulee on 17/3/1.
 */

public interface GitHubService {

    // 获取个人信息
    @GET("/users/{user}")
    Observable<GitHubUser> getUserData( @Path("user") String user );

    /**
     * 技术文章列表
     */
    @GET("data/{tech}/{num}/{page}")
    Observable<HttpResult<List<GankEntity>>> getGankData( @Path("tech") String tech,
            @Path("num") int num, @Path("page") int page );

    /**
     * 随机妹纸图
     */
    @GET("random/data/福利/{num}")
    Observable<HttpResult<List<GankEntity>>> getRandomGirl( @Path("num") int num );
}
