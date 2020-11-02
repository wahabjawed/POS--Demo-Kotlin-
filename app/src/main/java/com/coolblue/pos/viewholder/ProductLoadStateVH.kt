package com.coolblue.pos.viewholder

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.coolblue.pos.databinding.ProductLoadStateFooterBinding

class ProductLoadStateVH(
    private val binding: ProductLoadStateFooterBinding,
    private val retry: () -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {



    init {

        binding.btnRetry.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {

        binding.apply {
            progressBar.isVisible = loadState is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
            txtError.isVisible = loadState !is LoadState.Loading
        }


    }

}