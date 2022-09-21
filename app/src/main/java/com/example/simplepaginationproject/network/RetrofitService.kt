package com.example.simplepaginationproject.network

import com.example.simplepaginationproject.network.model.ImageResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path

interface RetrofitService {

    @GET("albums/{id}/photos")
    fun getImages(@Path("id")  id : Int): Call<ImageResponse>


    companion object {

        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}