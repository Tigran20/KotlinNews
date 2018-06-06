package com.alextroy.news22.api

import com.alextroy.news22.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApp {

    companion object {
        fun create(): NewsApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(NewsApi::class.java)
        }
    }
}