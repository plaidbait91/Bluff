package com.bphc.bluff

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun JoinRoomScreen(navController: NavController) {

    Surface(color = MaterialTheme.colors.background) {
        Text(text = "Hello World!", color = MaterialTheme.colors.onSurface)
    }

}