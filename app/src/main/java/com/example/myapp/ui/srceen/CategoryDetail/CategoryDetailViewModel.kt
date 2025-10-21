package com.example.myapp.ui.srceen.CategoryDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Data.Model.CategoryDetail
import com.example.myapp.Data.RecipeRepository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CategoryDetailUiState(
    val categoryDetail: CategoryDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class CategoryDetailViewModel : ViewModel() {
    private val repository: RecipeRepository = RecipeRepository()

    private val _uiState = MutableStateFlow(CategoryDetailUiState())
    val uiState: StateFlow<CategoryDetailUiState> = _uiState.asStateFlow()

    fun fetchCategoryDetails(categoryId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val detail = repository.getCategoryDetails(categoryId)
                _uiState.update { it.copy(isLoading = false, categoryDetail = detail) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Failed to load category details: ${e.message}") }
            }
        }
    }
}