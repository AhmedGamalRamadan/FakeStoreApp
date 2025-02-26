package com.ag.projects.fakestoreapp.presentation.di.viewmodel

import com.ag.projects.fakestoreapp.presentation.ui.screen.details.DetailsScreenViewModel
import com.ag.projects.fakestoreapp.presentation.ui.screen.home.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        HomeScreenViewModel(get(),get())
    }

    viewModel {
        DetailsScreenViewModel(get())
    }
}