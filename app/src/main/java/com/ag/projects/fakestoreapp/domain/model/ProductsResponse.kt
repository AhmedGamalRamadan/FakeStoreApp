package com.ag.projects.fakestoreapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductsResponse(
    val productsResponseItem: List<ProductsResponseItem>
)