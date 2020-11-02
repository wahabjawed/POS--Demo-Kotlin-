package com.coolblue.pos.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductDTO(
    val productId: Long,
    val productName: String,
    val reviewInformation: ReviewInformationDTO,
    val USPs: Array<String>,
    val availabilityState: Int,
    val salesPriceIncVat: Double,
    val productImage: String,
    val nextDayDelivery: Boolean,
    val promoIcon: PromoIconDTO?

) : Parcelable {

    @Parcelize
    data class ReviewInformationDTO(
        val reviewSummary: ReviewSummaryDTO
    ) : Parcelable {
        @Parcelize
        data class ReviewSummaryDTO(
            val reviewAverage: Float,
            val reviewCount: Int
        ) : Parcelable {

        }

    }

    @Parcelize
    data class PromoIconDTO(val text: String, val type: String) : Parcelable {

    }

}