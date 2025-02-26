package com.ag.projects.fakestoreapp.presentation.di.repository

import com.ag.projects.fakestoreapp.data.repository.RemoteProductsRepositoryImpl
import com.ag.projects.fakestoreapp.domain.repository.RemoteProductsRepository
import org.koin.dsl.module


val repositoryModule = module {

    single<RemoteProductsRepository> {
        RemoteProductsRepositoryImpl(get())
    }
}