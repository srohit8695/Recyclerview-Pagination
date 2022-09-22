package com.example.simplepaginationproject.adapter

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.simplepaginationproject.R
import com.example.simplepaginationproject.databinding.PhotoRvItemBinding
import com.example.simplepaginationproject.network.model.ImageResponseItem

class HomeScreenAdapter (val context: Context, var potoList : ArrayList<ImageResponseItem>) : RecyclerView.Adapter<HomeScreenAdapter.ViewHolder>(){

inner class ViewHolder(val binding: PhotoRvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val uiBinding = PhotoRvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(uiBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        try {
            with(holder){
                val data = potoList.get(position)
                binding.potoId.text = data.id.toString()
                binding.potoTitle.text = data.title
                binding.imageData.load(data.url){
                    placeholder(R.drawable.placeholder)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int = potoList.size

    fun updateList(dataList : ArrayList<ImageResponseItem>){
        potoList.addAll(dataList)
        notifyDataSetChanged()
    }



}