package com.example.submissionjetpack.ui.screen.cart

import com.example.submissionjetpack.model.OrderShoes

data class CartState(
    val orderShoes: List<OrderShoes>,
    val totalRequiredPoint: Int
)