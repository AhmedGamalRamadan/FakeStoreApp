package com.ag.projects.fakestoreapp.utils

import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {

    @Serializable
    data object Home : Screen

    @Serializable
    data class Details(val id: Int) : Screen
}