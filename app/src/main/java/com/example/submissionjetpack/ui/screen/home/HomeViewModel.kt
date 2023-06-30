package com.example.submissionjetpack.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionjetpack.data.ShoesRepository
import com.example.submissionjetpack.model.OrderShoes
import com.example.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ShoesRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderShoes>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderShoes>>>
        get() = _uiState

    fun getAllShoes() {
        viewModelScope.launch {
            repository.getAllShoes()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderShoes ->
                    _uiState.value = UiState.Success(orderShoes)
                }
        }
    }
}