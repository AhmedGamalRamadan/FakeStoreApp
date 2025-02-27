package com.ag.projects.fakestoreapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CategoryTabRow(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    categories: List<String>,
    onTabSelected: (Int) -> Unit
) {

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = modifier
            .fillMaxWidth()
        ) {

        categories.forEachIndexed { index, category ->
            Tab(
                selected = index == pagerState.currentPage,
                onClick = {
                    onTabSelected(index)
                },
                selectedContentColor = Color.Blue,
                unselectedContentColor = Color.Gray,
                modifier = modifier
                    .padding(10.dp)
            ) {
                Text(
                    text = category,
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}