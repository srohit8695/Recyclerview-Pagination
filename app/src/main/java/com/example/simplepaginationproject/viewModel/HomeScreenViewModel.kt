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

                    if (response.code() == 200) {
                        photoList.postValue(response.body())
                        if(numberIndex == maxIndex){
                            visibleFab.postValue(true)
                        }
                        if (numberIndex < maxIndex){
                            index = numberIndex+1
                            loadDatas(numberIndex+1, maxIndex)
                        }
                    } else if (response.code() == 404){
                        print("No data found")
                    } else if (response.code() == 403){
                    } else if (response.code() == 502){
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ImageResponse>, t: Throwable) {
                println(t.message.toString())
            }
        })
    }

}