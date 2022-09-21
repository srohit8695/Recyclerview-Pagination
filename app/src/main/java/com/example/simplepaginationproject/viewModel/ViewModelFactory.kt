package com.example.simplepaginationproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplepaginationproject.network.repository.APIRepository


class ViewModelFactory constructor (private val apiRepository : APIRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            HomeScreenViewModel(this.apiRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}