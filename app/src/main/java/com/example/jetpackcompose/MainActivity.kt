package com.example.jetpackcompose



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.*

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.jetpackcompose.ui.components.RallyTabRow
import com.example.jetpackcompose.ui.theme.RallyTheme


class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RallyApp()

        }
    }


    @Composable
    fun RallyApp() {
        RallyTheme {
            val allScreens = MainScreen.values().toList()
            var currentScreen by rememberSaveable { mutableStateOf(MainScreen.Overview) }
            Scaffold(
                topBar = {
                    RallyTabRow(
                        allScreens = allScreens,
                        onTabSelected = { screen -> currentScreen = screen },
                        currentScreen = currentScreen
                    )
                }
            ) { innerPadding ->
                Box(Modifier.padding(innerPadding)) {
                    currentScreen.content(
                        onScreenChange = { screen ->
                            currentScreen = MainScreen.valueOf(screen)
                        }
                    )
                }
            }
        }
    }
}
