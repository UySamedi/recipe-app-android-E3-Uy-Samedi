package com.example.myapp.ui.srceen.CategoryDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDetailScreen(
    navController: NavController,
    categoryId: String, // Accept categoryId directly
    categoryDetailViewModel: CategoryDetailViewModel = viewModel()
) {
    val uiState by categoryDetailViewModel.uiState.collectAsState()

    // Fetch data when the screen is first displayed
    LaunchedEffect(categoryId) {
        categoryDetailViewModel.fetchCategoryDetails(categoryId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = uiState.categoryDetail?.name ?: "Category") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.error != null) {
                Text(
                    text = uiState.error!!,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.Center).padding(16.dp)
                )
            } else {
                uiState.categoryDetail?.let { category ->
                    LazyColumn(contentPadding = PaddingValues(16.dp)) {
                        item {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current).data(category.imageUrl).crossfade(true).build(),
                                contentDescription = category.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth().height(250.dp).clip(RoundedCornerShape(16.dp))
                            )
                            Spacer(Modifier.height(16.dp))
                            Text(category.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(16.dp))
                            Text(category.description, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}