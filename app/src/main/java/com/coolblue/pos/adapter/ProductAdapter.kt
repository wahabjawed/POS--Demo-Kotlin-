package com.coolblue.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.coolblue.pos.data.ProductDTO
import com.coolblue.pos.databinding.ItemProductBinding
import com.coolblue.pos.viewholder.ProductVH

class ProductAdapter : PagingDataAdapter<ProductDTO, ProductVH>(PRODUCT_COMPARATOR) {
    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ProductVH(binding)
    }


    companion object {

        private val PRODUCT_COMPARATOR = object : DiffUtil.ItemCallback<ProductDTO>() {
            override fun areItemsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean =
                oldItem.productId == newItem.productId


            override fun areContentsTheSame(oldItem: ProductDTO, newItem: ProductDTO): Boolean =
                oldItem == newItem


        }
    }
}