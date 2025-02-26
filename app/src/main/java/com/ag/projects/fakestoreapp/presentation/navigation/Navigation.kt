package com.ag.projects.fakestoreapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ag.projects.fakestoreapp.presentation.ui.screen.details.DetailsScreen
import com.ag.projects.fakestoreapp.presentation.ui.screen.home.HomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){

        composable("home"){
            HomeScreen(
                navHostController = navController
            )
        }

        composable("details/{id}",
            arguments = listOf(
                navArgument("id"){type = NavType.IntType}
            )
        ){
            DetailsScreen(
                backStackEntry = it
            )
        }
    }
}