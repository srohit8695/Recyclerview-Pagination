package com.example.simplepaginationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.simplepaginationproject.databinding.ActivityMainBinding
import com.example.simplepaginationproject.network.RetrofitService
import com.example.simplepaginationproject.network.model.ImageResponse
import com.example.simplepaginationproject.network.repository.APIRepository
import com.example.simplepaginationproject.viewModel.HomeScreenViewModel
import com.example.simplepaginationproject.viewModel.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val retrofitService = RetrofitService.getInstance()
    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {

            viewModel = ViewModelProvider(this, ViewModelFactory(APIRepository(retrofitService)))[HomeScreenViewModel::class.java]

            loadData()

            binding.fabButton.setOnClickListener(View.OnClickListener {
                loadData()
            })

            viewModel.visibleFab.observe(this){
                if (it){
                    binding.fabButton.visibility = View.VISIBLE
                }
            }


            viewModel.photoList.observe(this){
                println("Response : "+it.toString())
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun loadData(){
        //Make FAB gone until loading is completed, so that user dont press again and again it might crash

        binding.fabButton.visibility = View.GONE
        viewModel.loadDatas(viewModel.index+1,viewModel.index+4)
    }


}