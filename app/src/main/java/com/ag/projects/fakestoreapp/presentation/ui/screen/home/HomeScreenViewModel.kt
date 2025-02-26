package com.ag.projects.fakestoreapp.presentation.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.domain.usecase.get.all.GetAllProductsUseCase
import com.ag.projects.fakestoreapp.domain.usecase.get.all.categories.GetCategoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import com.ag.projects.fakestoreapp.utils.Result
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _products = MutableStateFlow<Result<List<ProductsResponseItem>>>(Result.Loading)
    val products = _products.asStateFlow()


    init {
        getAllProducts()
    }

     fun getAllProducts() {
        viewModelScope.launch {

            getAllProductsUseCase.getAllProducts().collect { data ->
                when (data) {
                    is Result.Error -> {
                        _products.emit(Result.Error(data.message, data.exception))
                    }

                    Result.Loading -> {
                        _products.emit(Result.Loading)
                    }


                    is Result.Success -> {
                            _products.emit(
                                Result.Success(data.data)
                            )
                    }
                }
            }
        }
    }

    fun getCategories(category:String){
        viewModelScope.launch {

            getCategoriesUseCase.getCategory(category = category).collect{data->
                when (data) {
                    is Result.Error -> {
                        _products.emit(Result.Error(data.message, data.exception))
                    }

                    Result.Loading -> {
                        _products.emit(Result.Loading)
                    }


                    is Result.Success -> {
                        _products.emit(
                            Result.Success(data.data)
                        )
                    }
                }
            }
        }
    }
}