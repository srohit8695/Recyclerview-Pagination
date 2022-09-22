package com.example.simplepaginationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.simplepaginationproject.adapter.HomeScreenAdapter
import com.example.simplepaginationproject.databinding.ActivityMainBinding
import com.example.simplepaginationproject.network.RetrofitService
import com.example.simplepaginationproject.network.model.ImageResponse
import com.example.simplepaginationproject.network.model.ImageResponseItem
import com.example.simplepaginationproject.network.repository.APIRepository
import com.example.simplepaginationproject.util.Utils
import com.example.simplepaginationproject.viewModel.HomeScreenViewModel
import com.example.simplepaginationproject.viewModel.ViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val retrofitService = RetrofitService.getInstance()
    private lateinit var viewModel: HomeScreenViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : HomeScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        try {


                viewModel = ViewModelProvider(this, ViewModelFactory(APIRepository(retrofitService)))[HomeScreenViewModel::class.java]

                adapter = HomeScreenAdapter(this, arrayListOf())
                binding.photoRecyclerView.adapter = adapter

                loadData()

                binding.fabButton.setOnClickListener(View.OnClickListener {
                    loadData()
                })

                // Make the FAB visible only if the loading is complete or it may lead to some other problem
                viewModel.visibleFab.observe(this){
                    if (it){
                        binding.fabButton.visibility = View.VISIBLE
                    }
                }


                viewModel.photoList.observe(this){
                    adapter.updateList(it as ArrayList<ImageResponseItem>)
                }


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun loadData(){
        //Make FAB gone until loading is completed, so that user dont press again and again it might crash
        if (Utils.checkForInternet(this)) {
            binding.fabButton.visibility = View.GONE
            viewModel.loadDatas(viewModel.index+1,viewModel.index+4) {
                Utils.showShortToast(this, it)
            }
        } else {
            Utils.showShortToast(this,"Check Internet Connectivity")
        }
    }


}