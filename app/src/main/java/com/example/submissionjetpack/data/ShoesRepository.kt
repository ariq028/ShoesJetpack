package com.example.submissionjetpack.data

import com.example.submissionjetpack.model.FakeShoesDataSource
import com.example.submissionjetpack.model.OrderShoes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ShoesRepository {

    private val orderShoes = mutableListOf<OrderShoes>()

    init {
        if (orderShoes.isEmpty()) {
            FakeShoesDataSource.dummyShoes.forEach {
                orderShoes.add(OrderShoes(it, 0))
            }
        }
    }

    fun getAllShoes(): Flow<List<OrderShoes>> {
        return flowOf(orderShoes)
    }

    fun getOrderShoesById(shoesId: Long): OrderShoes {
        return orderShoes.first {
            it.shoes.id == shoesId
        }
    }

    fun updateOrderShoes(shoesId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderShoes.indexOfFirst { it.shoes.id == shoesId }
        val result = if (index >= 0) {
            val orderShoe = orderShoes[index]
            orderShoes[index] =
                orderShoe.copy(shoes = orderShoe.shoes, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderShoes(): Flow<List<OrderShoes>> {
        return getAllShoes()
            .map { orderShoes ->
                orderShoes.filter { orderShoes ->
                    orderShoes.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: ShoesRepository? = null

        fun getInstance(): ShoesRepository =
            instance ?: synchronized(this) {
                ShoesRepository().apply {
                    instance = this
                }
            }
    }
}