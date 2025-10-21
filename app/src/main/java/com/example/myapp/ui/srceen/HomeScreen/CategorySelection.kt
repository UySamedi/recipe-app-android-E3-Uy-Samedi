package com.example.myapp.ui.srceen.HomeScreen // Use your correct package

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.Data.Model.Category
import com.example.myapp.ui.theme.MyappTheme

@Composable
fun CategorySelection(
    categories: List<Category>,
    selectedCategory: Category?,
    onCategorySelected: (Category) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = category.id == selectedCategory?.id
            Button(
                onClick = { onCategorySelected(category) },
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {
                Text(text = category.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategorySelectionPreview() {
    val sampleCategories = listOf(
        Category(id = "1", name = "Breakfast", imageUrl = ""),
        Category(id = "2", name = "Lunch", imageUrl = ""),
        Category(id = "3", name = "Dinner", imageUrl = ""),
        Category(id = "4", name = "Dessert", imageUrl = "")
    )

    var selectedCategory by remember { mutableStateOf(sampleCategories[1]) }

    MyappTheme {
        CategorySelection(
            categories = sampleCategories,
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                // This makes the preview interactive
                selectedCategory = category
            }
        )
    }
}