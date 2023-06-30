package com.example.submissionjetpack.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpack.model.OrderShoes
import com.example.submissionjetpack.model.Shoes
import com.example.submissionjetpack.data.ShoesRepository
import com.example.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DetailShoesViewModel(
    private val repository: ShoesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderShoes>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderShoes>>
        get() = _uiState

    fun getShoesById(ShoesId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderShoesById(ShoesId))
        }
    }

    fun addToCart(Shoes: Shoes, count: Int) {
        viewModelScope.launch {
            repository.updateOrderShoes(Shoes.id, count)
        }
    }
}