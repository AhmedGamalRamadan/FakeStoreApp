package com.ag.projects.fakestoreapp.presentation.di.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val networkModule = module {

    single {
        HttpClient(Android) {
            expectSuccess = false

            install(ContentNegotiation) {
                json(
                    json = Json { ignoreUnknownKeys = true }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
            }
//            engine {
//                connectTimeout =10_000
//                socketTimeout =10_000
//            }
//            install(HttpTimeout) {
//                requestTimeoutMillis = 15000 // 15 seconds
//            }

            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = 3)
                exponentialDelay()
            }
        }
    }

}