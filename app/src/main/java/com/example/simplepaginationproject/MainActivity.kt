package com.example.simplepaginationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.simplepaginationproject.network.RetrofitService
import com.example.simplepaginationproject.network.model.ImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        try {
            print("Response : ")
            val response = retrofitService.getImages(1)
            response.enqueue(object : Callback<ImageResponse>{
                override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                    try {
                        print("Response : "+response.body().toString())
                        Toast.makeText(baseContext,response.body().toString(),Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                    
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }



}