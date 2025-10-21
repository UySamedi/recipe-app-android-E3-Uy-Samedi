package com.example.myapp.ui.srceen.FavoritesScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Data.FavoritesRepository
import com.example.myapp.Data.Model.Meal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FavoritesUiState(
    val favoriteMeals: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class FavoritesViewModel(
    private val favoritesRepository: FavoritesRepository = FavoritesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            favoritesRepository.getFavorites().collect { favorites ->
                _uiState.update {
                    it.copy(favoriteMeals = favorites, isLoading = false)
                }
            }
        }
    }

    fun toggleFavorite(meal: Meal) {
        favoritesRepository.toggleFavorite(meal)
    }
}