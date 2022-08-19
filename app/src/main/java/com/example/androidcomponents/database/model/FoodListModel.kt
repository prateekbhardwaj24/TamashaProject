package com.example.androidcomponents.database.model

data class FoodListModel(
    var foodImage: String,
    var foodName: String,
    var foodPrice: String,
    var foodRating: String,
    var foodDesc: String,
    var foodOffer: String,
    var foodLayout: String,
    var foodExtendedOffer: String
)

data class FoodImageListModel(
    var foodImage: String,
    var foodName: String
)

data class FoodGridListModel(
    var foodImage: String,
    var foodName: String
)
