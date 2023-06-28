package com.bphc.bluff

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

private fun boolToInt(b: Boolean) = if(b) 1 else 0

@Composable
fun SettingScreen(navController: NavController = rememberNavController(), viewModel: SettingViewModel) {

    val options = listOf("Off", "On")
    var displayName by rememberSaveable { mutableStateOf(viewModel.getDisplayName()) }
    val music by viewModel.music.collectAsState()
    val sound by viewModel.sound.collectAsState()

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.fillMaxSize(0.075f))

            Text(text = "Settings",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(16.dp))

            Spacer(modifier = Modifier.fillMaxSize(0.075f))

            Column(modifier = Modifier.padding(bottom = 12.dp)){
                Text(text = "Display name", fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(4.dp))
                OutlinedTextField(value = displayName,
                    onValueChange = {
                        displayName = if(it.length <= 64) it
                        else it.substring(0, 64)
                    },
                    maxLines = 1)
            }

            DropdownComponent(options, "Music", 24.dp, boolToInt(music)) {
                viewModel.setMusic(options[1] == it)
            }

            DropdownComponent(options, "SFX", 24.dp, boolToInt(sound)) {
                viewModel.setSound(options[1] == it)
            }

            Icon(imageVector = Icons.Outlined.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .size(48.dp)
                    .clickable {
                        viewModel.setDisplayName(displayName)
                        navController.popBackStack()
                    })
        }

    }

}

//
//
//@Preview
//@Composable
//fun SettingPreview() {
//
//    SettingScreen()
//
//}