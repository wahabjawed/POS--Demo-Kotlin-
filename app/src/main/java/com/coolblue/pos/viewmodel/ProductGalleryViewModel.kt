package com.coolblue.pos.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.coolblue.pos.data.ProductRepository

class ProductGalleryViewModel @ViewModelInject constructor(
    private val productRepository: ProductRepository,
    @Assisted state: SavedStateHandle
) :
    ViewModel() {


    private val searchQuery = MutableLiveData("")
    val products = searchQuery.switchMap { queryString ->
        productRepository.getSearchResults(queryString).cachedIn(viewModelScope)
    }

    fun searchProducts(query: String) {
        searchQuery.value = query
    }
}
