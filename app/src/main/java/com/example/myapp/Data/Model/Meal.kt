package com.example.myapp.Data.Model


import com.google.gson.annotations.SerializedName

data class Meal(
    @SerializedName("id") val id: String,
    @SerializedName("meal") val name: String,
    @SerializedName("mealThumb") val imageUrl: String,
    @SerializedName("category") val category: String,
    val isFavorite: Boolean = false
)
