package com.example.myapp.Data.Model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("category") val name: String,
    @SerializedName("categoryThumb") val imageUrl: String
)