package com.ag.projects.fakestoreapp.presentation.di.usecase

import com.ag.projects.fakestoreapp.domain.usecase.get.GetProductUseCase
import com.ag.projects.fakestoreapp.domain.usecase.get.all.GetAllProductsUseCase
import com.ag.projects.fakestoreapp.domain.usecase.get.all.categories.GetCategoriesUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single{
        GetAllProductsUseCase(get())
    }

    single {
        GetCategoriesUseCase(get())
    }


    single {
        GetProductUseCase(get())
    }
}