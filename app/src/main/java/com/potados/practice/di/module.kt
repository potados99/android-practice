package com.potados.practice.di

import com.potados.practice.data.BGMovieProvider
import com.potados.practice.data.BGMovieRepository
import com.potados.practice.data.BGMovieRepositoryImpl
import com.potados.practice.model.ItemDetailFragmentModel
import com.potados.practice.viewmodel.ItemDetailFragmentViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val appModule: Module = module {
    single {
        BGMovieRepositoryImpl("hey") as BGMovieRepository /* the provider takes type of interface. */
    }

    single {
        BGMovieProvider(get())
    }

    single {
        ItemDetailFragmentViewModel(get())
    }
}