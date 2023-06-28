package com.bphc.bluff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.bphc.bluff.ui.theme.BluffTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BluffTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screens.MAIN_SCREEN) {
                        composable(Screens.MAIN_SCREEN) { MainScreen(navController) }
                        composable(Screens.CREATE_ROOM_SCREEN) { CreateRoomScreen(navController) }
                        composable(Screens.JOIN_ROOM_SCREEN) { JoinRoomScreen(navController) }
                        composable(Screens.RULES_SCREEN) { RulesScreen(navController) }
                        composable(Screens.SETTINGS_SCREEN) {
                            val viewModel = hiltViewModel<SettingViewModel>()
                            SettingScreen(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BluffTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MainScreen(navController = rememberNavController())
        }
    }
}