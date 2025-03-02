package com.ag.projects.fakestoreapp.presentation.ui.screen.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ag.projects.fakestoreapp.presentation.ui.components.MediumSpacerHeight
import com.ag.projects.fakestoreapp.presentation.ui.components.RatingBar
import com.ag.projects.fakestoreapp.utils.Result
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    productId: Int,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    val viewModel: DetailsScreenViewModel = getViewModel()
    val productState by viewModel.product.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        productId?.let { id ->
            viewModel.getProductById(id)
        }
    }

    when (productState) {
        is Result.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = (productState as Result.Error).message)
            }
        }

        Result.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is Result.Success -> {

            val product = (productState as Result.Success).data

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(12.dp)
            ) {
                MediumSpacerHeight()

                AsyncImage(
                    model = product.image,
                    contentDescription = product.title,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .padding(8.dp)
                        .sharedElement(
                            state = rememberSharedContentState("image/${product.image}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(1000)
                            }
                        ),
                )

                MediumSpacerHeight()

                Row(
                    modifier = modifier
                        .fillMaxWidth(),
                ) {
                    product.title?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = modifier
                                .weight(1f)
                                .sharedElement(
                                    state = rememberSharedContentState("title/${product.title}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(1000)
                                    }
                                )
                        )
                    }

                    product.price?.let {
                        Text(
                            text = "$it $",
                            textAlign = TextAlign.End,
                            color = Color.Green,
                            modifier = modifier
                                .padding(4.dp)
                                .sharedElement(
                                    state = rememberSharedContentState("price/${product.price}"),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    boundsTransform = { _, _ ->
                                        tween(1000)
                                    }
                                )
                        )
                    }
                }

                MediumSpacerHeight()

                Text(
                    text = "Description:",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    )

                product.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                MediumSpacerHeight()

                Text(
                    text = "Rate",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                product.rating?.rate?.let {
                    RatingBar(
                        rating = it,
                        starsModifier = modifier
                            .size(50.dp)
                    )
                }
            }
        }
    }
}