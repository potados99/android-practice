package com.potados.practice.di

import com.potados.practice.data.movie.BGMovieProvider
import com.potados.practice.data.movie.BGMovieRepository
import com.potados.practice.data.movie.BGMovieRepositoryImpl
import com.potados.practice.data.storage.StorageInfoProvider
import com.potados.practice.data.storage.StorageInfoRepository
import com.potados.practice.data.storage.StorageInfoRepositoryImpl
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule: Module = module {
    single { BGMovieRepositoryImpl() as BGMovieRepository }
    single { BGMovieProvider(get()) }

    single { StorageInfoProvider(get()) }
    single { StorageInfoRepositoryImpl() as StorageInfoRepository }

    single { ItemDetailFragmentViewModel(get()) }
}