package com.example.submissionjetpack.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpack.data.ShoesRepository
import com.example.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ShoesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderShoes() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderShoes()
                .collect { orderShoes ->
                    val totalRequiredPoint =
                        orderShoes.sumOf { it.shoes.requiredPoint * it.count }
                    _uiState.value = UiState.Success(CartState(orderShoes, totalRequiredPoint))
                }
        }
    }

    fun updateOrderShoes(ShoesId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderShoes(ShoesId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderShoes()
                    }
                }
        }
    }
}