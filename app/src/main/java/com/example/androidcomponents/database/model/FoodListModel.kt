package com.example.androidcomponents.database.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class FoodListModel(
    @SerializedName("foodImage") var foodImage: String,
    @SerializedName("foodName") var foodName: String,
    @SerializedName("foodPrice") var foodPrice: String,
    @SerializedName("foodRating") var foodRating: String,
    @SerializedName("foodDesc") var foodDesc: String,
    @SerializedName("foodOffer") var foodOffer: String,
    @SerializedName("foodLayout") var foodLayout: String,
    @SerializedName("foodExtendedOffer") var foodExtendedOffer: String
) : Serializable

data class FoodImageListModel(
    @SerializedName("foodImage") var foodImage: String,
    @SerializedName("foodName") var foodName: String
) : Serializable

data class FoodGridListModel(
    @SerializedName("foodImage") var foodImage: String,
    @SerializedName("foodName") var foodName: String
) : Serializable
