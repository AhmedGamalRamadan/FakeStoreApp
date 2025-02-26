package com.ag.projects.fakestoreapp.presentation.ui.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.domain.usecase.get.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import com.ag.projects.fakestoreapp.utils.Result
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {


    private val _product = MutableStateFlow<Result<ProductsResponseItem>>(Result.Loading)
    val product = _product.asStateFlow()

    fun getProductById(id: Int) {
        viewModelScope.launch {
            try {
                when (val productDetails = getProductUseCase.getProductById(id)) {
                    is Result.Error -> {
                        _product.emit(
                            Result.Error(
                                productDetails.message,
                                productDetails.exception
                            )
                        )

                    }

                    Result.Loading -> {
                        _product.emit(Result.Loading)
                    }

                    is Result.Success -> {
                        _product.emit(Result.Success(productDetails.data))
                    }
                }

            } catch (e: Exception) {
                _product.emit(Result.Error(e.message.toString(), e))
            }
        }
    }

}