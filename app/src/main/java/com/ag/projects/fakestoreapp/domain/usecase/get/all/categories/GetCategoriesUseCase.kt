package com.ag.projects.fakestoreapp.domain.usecase.get.all.categories

import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.domain.repository.RemoteProductsRepository
import com.ag.projects.fakestoreapp.utils.Result
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(
    private val remoteProductsRepository: RemoteProductsRepository
) {

    suspend fun getCategory(category: String): Flow<Result<List<ProductsResponseItem>>> =
        remoteProductsRepository.getCategory(category)


}