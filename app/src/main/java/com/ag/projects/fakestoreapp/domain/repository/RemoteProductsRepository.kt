package com.ag.projects.fakestoreapp.domain.repository

import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.utils.Result
import kotlinx.coroutines.flow.Flow

interface RemoteProductsRepository {

    suspend fun getAllProducts(): Flow<Result<List<ProductsResponseItem>>>

    suspend fun getCategory(category:String): Flow<Result<List<ProductsResponseItem>>>

    suspend fun getProductById(productId:Int): Result<ProductsResponseItem>


}