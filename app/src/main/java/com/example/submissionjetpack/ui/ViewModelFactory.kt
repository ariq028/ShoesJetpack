package com.example.submissionjetpack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionjetpack.ui.screen.cart.CartViewModel
import com.example.submissionjetpack.ui.screen.detail.DetailShoesViewModel
import com.example.submissionjetpack.ui.screen.home.HomeViewModel
import com.example.submissionjetpack.data.ShoesRepository

class ViewModelFactory(private val repository: ShoesRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailShoesViewModel::class.java)) {
            return DetailShoesViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}