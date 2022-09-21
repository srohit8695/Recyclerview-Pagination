package com.example.simplepaginationproject.viewModel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplepaginationproject.network.model.ImageResponse
import com.example.simplepaginationproject.network.model.ImageResponseItem
import com.example.simplepaginationproject.network.repository.APIRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.coroutineContext

class HomeScreenViewModel(private val repository: APIRepository) : ViewModel() {

    val photoList = MutableLiveData<List<ImageResponseItem>>()
    var index = 0
    var visibleFab = MutableLiveData<Boolean>()


    fun loadDatas(numberIndex : Int, maxIndex : Int){
        val response = repository.getPhotos(numberIndex)
        response.enqueue(object : Callback<ImageResponse> {
            override fun onResponse(call: Call<ImageResponse>, response: Response<ImageResponse>) {
                try {
                    print("Response : "+response.body().toString())
                    photoList.postValue(response.body())
                    if(numberIndex == maxIndex){
                        visibleFab.postValue(true)
                    }
                    if (numberIndex < maxIndex){
                        index = numberIndex+1
                        loadDatas(numberIndex+1, maxIndex)
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {

            }
        })
    }

}