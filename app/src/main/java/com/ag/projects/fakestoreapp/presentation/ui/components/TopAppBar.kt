package com.ag.projects.fakestoreapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeStoreTopAppBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = "FakeStoreApp")
        },
        modifier = modifier
            .fillMaxWidth(),
        scrollBehavior = scrollBehavior,
        colors =  TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        actions = {
            ThemeSwitcher(
                darkTheme = darkTheme,
                onClick = { onThemeUpdated() }
            )
        }
    )
}