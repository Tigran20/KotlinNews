package com.alextroy.news22.api


import com.alextroy.news22.model.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("search")
    fun getNews(
            @Query("show-fields") show_fields: String,
            @Query("api-key") key: String
    ): Observable<News>
}