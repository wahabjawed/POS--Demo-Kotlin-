package com.coolblue.pos.api

import com.coolblue.pos.data.ProductDTO

data class ProductResponse(
    val currentPage: Int,
    val pageSize: Int,
    val totalResults: Int,
    val pageCount: Int,
    val products: List<ProductDTO>
)