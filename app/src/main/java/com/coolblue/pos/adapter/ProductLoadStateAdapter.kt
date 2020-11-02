package com.coolblue.pos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.coolblue.pos.databinding.ProductLoadStateFooterBinding
import com.coolblue.pos.viewholder.ProductLoadStateVH

class ProductLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<ProductLoadStateVH>() {
    override fun onBindViewHolder(holder: ProductLoadStateVH, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ProductLoadStateVH {
        val binding = ProductLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductLoadStateVH(binding, retry)
    }
}