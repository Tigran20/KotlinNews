package com.alextroy.news22.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApp {

    companion object {
        private const val BASE_URL = "http://content.guardianapis.com/?&show-fields=thumbnail,trailText/"

        fun create(): NewsApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(NewsApi::class.java)
        }
    }
}