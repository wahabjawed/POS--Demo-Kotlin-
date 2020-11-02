package com.coolblue.pos.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.coolblue.pos.R
import com.coolblue.pos.data.ProductDTO
import com.coolblue.pos.databinding.ItemProductBinding
import com.coolblue.pos.util.StringUtil

class ProductVH(private val binding: ItemProductBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: ProductDTO?) {

        binding.apply {
            txtName.text = item!!.productName
            ratingBar.rating = item.reviewInformation.reviewSummary.reviewAverage/2
            txtRating.text = "(${item.reviewInformation.reviewSummary.reviewCount})"
            txtPrice.text = StringUtil.euroFormat(item.salesPriceIncVat)

            Glide.with(itemView).load(item.productImage).centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade()).error(R.drawable.ic_error)
                .into(imageView)
        }
    }


}