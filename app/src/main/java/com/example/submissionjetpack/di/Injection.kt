package com.example.submissionjetpack.di

import com.example.submissionjetpack.data.ShoesRepository


object Injection {
    fun provideRepository(): ShoesRepository {
        return ShoesRepository.getInstance()
    }
}