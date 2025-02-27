package com.ag.projects.fakestoreapp.presentation.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.ag.projects.fakestoreapp.presentation.ui.components.CategoryTabRow
import com.ag.projects.fakestoreapp.presentation.ui.components.FakeStoreTopAppBar
import com.ag.projects.fakestoreapp.presentation.ui.components.ProductItem
import com.ag.projects.fakestoreapp.utils.Result
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(
    ExperimentalMaterial3Api::class,
)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {

    val viewModel: HomeScreenViewModel = getViewModel()
    val productsResponse by viewModel.products.collectAsStateWithLifecycle()

    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()


    val categories = listOf(
        "All",
        "electronics",
        "jewelery",
        "men's clothing",
        "women's clothing"
    )
    val pagerState = rememberPagerState { categories.size }

    val scope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            if (page == 0) viewModel.getAllProducts()
            else viewModel.getCategories(categories[page])
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            FakeStoreTopAppBar(
                modifier = modifier,
                scrollBehavior = scrollBehaviour
            )
        }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            CategoryTabRow(
                pagerState = pagerState,
                categories = categories,
                onTabSelected = { index ->
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )

            when (productsResponse) {
                is Result.Error -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = (productsResponse as Result.Error).message)
                    }
                }

                Result.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is Result.Success -> {
                    val products = (productsResponse as Result.Success).data

                    HorizontalPager(
                        state = pagerState,
                    ) {

                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(150.dp),
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            items(products) { product ->
                                ProductItem(
                                    product = product,
                                    navHostController = navHostController
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}