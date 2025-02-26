package com.ag.projects.fakestoreapp.domain.usecase.get

import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.domain.repository.RemoteProductsRepository
import com.ag.projects.fakestoreapp.utils.Result

class GetProductUseCase(
    private val remoteProductsRepository: RemoteProductsRepository
) {

    suspend fun getProductById(productId: Int): Result<ProductsResponseItem> =
        remoteProductsRepository.getProductById(productId)
}