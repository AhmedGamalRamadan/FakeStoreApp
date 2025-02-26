package com.ag.projects.fakestoreapp

import android.app.Application
import com.ag.projects.fakestoreapp.presentation.di.network.networkModule
import com.ag.projects.fakestoreapp.presentation.di.repository.repositoryModule
import com.ag.projects.fakestoreapp.presentation.di.usecase.useCaseModule
import com.ag.projects.fakestoreapp.presentation.di.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FakeStoreApp :Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FakeStoreApp)
            modules(
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}