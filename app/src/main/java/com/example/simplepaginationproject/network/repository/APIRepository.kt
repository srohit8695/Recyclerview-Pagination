package com.example.simplepaginationproject.network.repository

import com.example.simplepaginationproject.network.RetrofitService

class APIRepository constructor(private val retrofitService: RetrofitService){
    fun getPhotos(id : Int) = retrofitService.getImages(id)
}