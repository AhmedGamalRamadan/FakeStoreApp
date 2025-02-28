package com.ag.projects.fakestoreapp.presentation.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ag.projects.fakestoreapp.presentation.ui.screen.details.DetailsScreen
import com.ag.projects.fakestoreapp.presentation.ui.screen.home.HomeScreen
import com.ag.projects.fakestoreapp.utils.Screen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    SharedTransitionLayout {

        NavHost(
            navController = navController,
            startDestination = Screen.Home
        ) {

            composable<Screen.Home> {
                HomeScreen(
                    navHostController = navController,
                    animatedVisibilityScope = this
                )
            }

            composable<Screen.Details> {
                val args = it.toRoute<Screen.Details>()
                DetailsScreen(
                    productId = args.id,
                    animatedVisibilityScope = this
                )
            }
        }
    }

}