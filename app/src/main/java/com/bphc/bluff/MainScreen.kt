package com.bphc.bluff

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(navController: NavController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {

                MainMenuOption(
                    title = "Create New Room",
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable.create_room)
                ) {
                    navController.navigate("create_room_screen")
                }

                MainMenuOption(
                    title = "Join Existing Room",
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable.join_room)
                ) {
                    navController.navigate("join_room_screen")
                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {

                MainMenuOption(
                    title = "Rules",
                    modifier = Modifier.weight(1f),
                    painter = rememberVectorPainter(image = Icons.Filled.List)
                ) {
                    navController.navigate("rules_screen")
                }

                MainMenuOption(
                    title = "Settings",
                    modifier = Modifier.weight(1f),
                    painter = rememberVectorPainter(image = Icons.Filled.Settings)
                ) {
                    navController.navigate("settings_screen")
                }

            }

        }
    }


}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(navController = rememberNavController())
}





