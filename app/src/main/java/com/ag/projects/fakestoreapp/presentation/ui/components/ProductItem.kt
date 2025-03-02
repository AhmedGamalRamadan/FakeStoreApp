package com.ag.projects.fakestoreapp.presentation.ui.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.ag.projects.fakestoreapp.R
import com.ag.projects.fakestoreapp.domain.model.ProductsResponseItem
import com.ag.projects.fakestoreapp.utils.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ProductItem(
    modifier: Modifier = Modifier,
    product: ProductsResponseItem,
    navHostController: NavHostController,
    animatedVisibilityScope: AnimatedVisibilityScope
) {

    Card(
        modifier = modifier
            .wrapContentHeight()
            .width(200.dp)
            .padding(8.dp)
            .clickable {
                product.id?.let { id ->
                    navHostController.navigate(Screen.Details(id))
                }
            },
        shape = RoundedCornerShape(7.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(
            modifier = modifier
                .wrapContentHeight()
                .padding(5.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .sharedElement(
                        state = rememberSharedContentState("image/${product.image}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(1000)
                        }
                    ),
                placeholder = painterResource(R.drawable.ic_launcher_background)
            )

            Spacer(Modifier.height(7.dp))

            product.title?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier
                        .sharedElement(
                        state = rememberSharedContentState("title/${product.title}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(1000)
                        }
                    )
                )
            }

            Spacer(Modifier.height(7.dp))

            product.price?.let {
                Text(
                    text = "$it $",
                    fontSize = 18.sp,
                    color = Color.Green,
                    modifier = modifier
                        .sharedElement(
                            state = rememberSharedContentState("price/${product.price}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(1000)
                            }
                        )
                )
            }

            product.rating?.let {
                RatingBar(
                    rating = it.rate!!
                )
            }
        }

    }
}