package com.coolblue.pos.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.coolblue.pos.api.CoolBlueAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val coolBlueAPI: CoolBlueAPI) {

    fun getSearchResults(query: String) = Pager(
        config = PagingConfig(pageSize = 24, maxSize = 100, enablePlaceholders = false),
        pagingSourceFactory = { ProductSearchPageSource(coolBlueAPI, query) }
    ).liveData


}

