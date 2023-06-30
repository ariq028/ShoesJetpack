package com.example.submissionjetpack.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissionjetpack.di.Injection
import com.example.submissionjetpack.model.OrderShoes
import com.example.submissionjetpack.ui.ViewModelFactory
import com.example.submissionjetpack.ui.common.UiState
import com.example.submissionjetpack.ui.components.ShoesItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllShoes()
            }
            is UiState.Success -> {
                HomeContent(
                    orderShoes = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun HomeContent(
    orderShoes: List<OrderShoes>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(orderShoes) { data ->
            ShoesItem(
                image = data.shoes.image,
                title = data.shoes.title,
                requiredPoint = data.shoes.requiredPoint,
                modifier = Modifier.clickable {
                    navigateToDetail(data.shoes.id)
                }
            )
        }
    }
}