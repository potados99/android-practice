package com.potados.practice.di

import com.potados.practice.data.BGMovieProvider
import com.potados.practice.data.BGMovieRepository
import com.potados.practice.data.BGMovieRepositoryImpl
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule: Module = module {
    single {

        BGMovieProvider(BGMovieRepositoryImpl("hey"))
    }
}