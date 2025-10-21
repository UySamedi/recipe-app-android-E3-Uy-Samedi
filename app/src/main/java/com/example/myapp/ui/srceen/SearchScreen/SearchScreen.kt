package com.example.myapp.ui.srceen.SearchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Data.Model.Meal
import com.example.myapp.Data.RecipeRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SearchUiState(
    val searchQuery: String = "",
    val searchResults: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val nothingFound: Boolean = false
)

@OptIn(FlowPreview::class)
class SearchViewModel : ViewModel() {
    private val repository = RecipeRepository()
    private var allMeals: List<Meal> = emptyList()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val searchQuery = MutableStateFlow("")

    init {
        fetchAllMeals()
        viewModelScope.launch {
            searchQuery
                .debounce(300)
                .distinctUntilChanged()
                .collect { query ->
                    filterMeals(query)
                }
        }
    }

    private fun fetchAllMeals() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                allMeals = repository.searchAllMeals()
                _uiState.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = "Failed to load meals.") }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        searchQuery.value = query
    }

    private fun filterMeals(query: String) {
        if (query.isBlank()) {
            _uiState.update { it.copy(searchResults = emptyList(), nothingFound = false) }
            return
        }
        val results = allMeals.filter {
            it.name.contains(query, ignoreCase = true)
        }
        _uiState.update {
            it.copy(
                searchResults = results,
                nothingFound = results.isEmpty()
            )
        }
    }
}
