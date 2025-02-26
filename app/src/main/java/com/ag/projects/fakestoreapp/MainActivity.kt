package com.ag.projects.fakestoreapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ag.projects.fakestoreapp.presentation.navigation.Navigation
import com.ag.projects.fakestoreapp.presentation.ui.theme.FakeStoreAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FakeStoreAppTheme {
                    Navigation()
            }
        }
    }
}