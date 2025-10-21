package com.example.myapp.Data

import com.example.myapp.Data.Model.Category
import com.example.myapp.Data.Model.CategoryDetail
import com.example.myapp.Data.Model.Meal
import com.example.myapp.Data.Model.MealDetail
import com.example.myapp.Data.Network.ApiService
import com.example.myapp.Data.Network.RetrofitInstance

open class RecipeRepository(private val apiService: ApiService = RetrofitInstance.api) {

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): T? {
        return try {
            apiCall.invoke()
        } catch (e: Exception) {
            null
        }
    }

    open suspend fun getMeals(): List<Meal> = safeApiCall { apiService.getMeals() } ?: emptyList()
    open suspend fun getCategories(): List<Category> = safeApiCall { apiService.getCategories() } ?: emptyList()
    suspend fun getMealDetails(mealId: String): MealDetail? = safeApiCall { apiService.getMealDetails(mealId) }
    suspend fun getCategoryDetails(categoryId: String): CategoryDetail? = safeApiCall { apiService.getCategoryDetails(categoryId) }
    suspend fun searchAllMeals(): List<Meal> = safeApiCall { apiService.searchAllMeals() } ?: emptyList()
}