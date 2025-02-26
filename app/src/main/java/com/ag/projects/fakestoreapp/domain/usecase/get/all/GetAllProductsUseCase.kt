package com.ag.projects.fakestoreapp.domain.usecase.get.all

import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.domain.repository.RemoteProductsRepository
import com.ag.projects.fakestoreapp.utils.Result
import kotlinx.coroutines.flow.Flow

class GetAllProductsUseCase(
    private val productsRepository: RemoteProductsRepository
) {
    suspend fun getAllProducts(): Flow<Result<List<ProductsResponseItem>>> =
        productsRepository.getAllProducts()
}