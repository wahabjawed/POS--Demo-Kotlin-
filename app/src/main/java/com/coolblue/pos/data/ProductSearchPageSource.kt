package com.coolblue.pos.data

import androidx.paging.PagingSource
import com.coolblue.pos.api.CoolBlueAPI
import com.coolblue.pos.constant.AppConstant
import retrofit2.HttpException
import java.io.IOException

class ProductSearchPageSource(
    private val coolBlueAPI: CoolBlueAPI,
    private val query: String
) : PagingSource<Int, ProductDTO>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductDTO> {
        var position = params.key ?: AppConstant.PAGE_START_INDEX

        return try {
            val response = coolBlueAPI.searchProducts(query, position)
            LoadResult.Page(
                data = response.products,
                prevKey = if (position == AppConstant.PAGE_START_INDEX) null else position - 1,
                nextKey = if (response.products.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}