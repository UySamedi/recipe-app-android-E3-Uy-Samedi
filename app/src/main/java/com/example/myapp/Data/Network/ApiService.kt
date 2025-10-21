package com.example.myapp.Data.Network

import com.example.myapp.Data.Model.Category
import com.example.myapp.Data.Model.CategoryDetail
import com.example.myapp.Data.Model.Meal
import com.example.myapp.Data.Model.MealDetail
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("meals")
    suspend fun getMeals(@Header("X-DB-NAME") dbName: String = ApiConstants.DB_NAME): List<Meal>

    @GET("categories")
    suspend fun getCategories(@Header("X-DB-NAME") dbName: String = ApiConstants.DB_NAME): List<Category>

    @GET("meals/{id}")
    suspend fun getMealDetails(
        @Path("id") mealId: String,
        @Header("X-DB-NAME") dbName: String = ApiConstants.DB_NAME
    ): MealDetail

    @GET("categories/{id}")
    suspend fun getCategoryDetails(
        @Path("id") categoryId: String,
        @Header("X-DB-NAME") dbName: String = ApiConstants.DB_NAME
    ): CategoryDetail

    @GET("meals")
    suspend fun searchAllMeals(@Header("X-DB-NAME") dbName: String = ApiConstants.DB_NAME): List<Meal>
}