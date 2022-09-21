package com.example.simplepaginationproject.network.model


import com.google.gson.annotations.SerializedName

class ImageResponse : ArrayList<ImageResponseItem>()

data class ImageResponseItem(
    @SerializedName("albumId")
    val albumId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)