package com.ag.projects.fakestoreapp.data.repository

import android.util.Log
import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.domain.repository.RemoteProductsRepository
import com.ag.projects.fakestoreapp.utils.Constants
import com.ag.projects.fakestoreapp.utils.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteProductsRepositoryImpl(
    private val httpClient: HttpClient
) : RemoteProductsRepository {

    override suspend fun getAllProducts(): Flow<Result<List<ProductsResponseItem>>> {
        return flow {
            try {
                val data: HttpResponse =
                    httpClient.get("https://fakestoreapi.com/products")
                val responseBody = data.bodyAsText()

                if (responseBody.isBlank()) {
                    Log.e("home:", "Error: Empty response from server")
                    emit(Result.Error("Empty response from server"))
                    return@flow
                }
                emit(Result.Success(data.body()))

            } catch (e: Exception) {
                Result.Error(e.message.toString(), e)
            }
        }
    }

    override suspend fun getCategory(category: String): Flow<Result<List<ProductsResponseItem>>> {
        return flow {
            try {
                val data: HttpResponse =
                    httpClient.get(
                        "https://fakestoreapi.com/products/category/$category"
                    )

                val responseBody = data.bodyAsText()

                if (responseBody.isBlank()) {
                    Log.e("home:", "Error: Empty response from server")
                    emit(Result.Error("Empty response from server"))
                    return@flow
                }
                emit(Result.Success(data.body()))

            } catch (e: Exception) {
                Result.Error(e.message.toString(), e)
            }
        }
    }

    override suspend fun getProductById(productId: Int): Result<ProductsResponseItem> {
        return try {
            val data :HttpResponse = httpClient.get(
                "https://fakestoreapi.com/products/$productId"
            )

            if (data.bodyAsText().isBlank()){
                return Result.Error("Empty response from server")
            }
            Result.Success(data.body())

        }catch (e:Exception){
            Result.Error(e.message.toString(),e)
        }
    }
}