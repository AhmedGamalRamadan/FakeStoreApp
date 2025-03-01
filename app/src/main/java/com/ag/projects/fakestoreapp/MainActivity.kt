package com.ag.projects.fakestoreapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.edit
import com.ag.projects.fakestoreapp.presentation.navigation.Navigation
import com.ag.projects.fakestoreapp.presentation.ui.theme.FakeStoreAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val themeSharedPreferences = getSharedPreferences("theme", Context.MODE_PRIVATE)

        enableEdgeToEdge()
        setContent {
            var themeState by remember {
                mutableStateOf(
                    themeSharedPreferences.getBoolean("theme", false)
                )
            }
            FakeStoreAppTheme(
                darkTheme = themeState
            ) {
                Navigation(
                    darkTheme = themeState,
                    onThemeUpdated = {
                        themeState = !themeState
                        themeSharedPreferences.edit {
                            putBoolean("theme",themeState)
                        }
                    }
                )
            }
        }
    }
}