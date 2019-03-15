package com.potados.practice.di

import com.potados.practice.data.movie.BGMovieProvider
import com.potados.practice.data.movie.BGMovieRepository
import com.potados.practice.data.movie.BGMovieRepositoryImpl
import com.potados.practice.data.storage.ExternalStorageProvider
import com.potados.practice.data.storage.ExternalStorageRepository
import com.potados.practice.data.storage.ExternalStorageRepositoryImpl
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule: Module = module {
    single { BGMovieRepositoryImpl() as BGMovieRepository }
    single { BGMovieProvider(get()) }

    single { ExternalStorageProvider(get()) }
    single { ExternalStorageRepositoryImpl() as ExternalStorageRepository }

    single { ItemDetailFragmentViewModel(get()) }
}