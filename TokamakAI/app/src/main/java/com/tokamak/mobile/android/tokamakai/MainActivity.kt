package com.tokamak.mobile.android.tokamakai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.tokamak.mobile.android.tokamakai.model.ReflectRepo
import com.tokamak.mobile.android.tokamakai.ui.HomeScreen
import com.tokamak.mobile.android.tokamakai.ui.SettingsScreen
import com.tokamak.mobile.android.tokamakai.ui.theme.ReflectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReflectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    ReflectNavHost()
                }
            }
        }
    }
}

/*
 * Host for navigating between main screen and settings screen
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReflectNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberAnimatedNavController(),
    startDestination: String = "home"
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            "home",
        ) {
            HomeScreen(
                onEditTracker = {
                    val index = ReflectRepo.model.getIndex(it)
                    navController.navigate("settings/$index")
                },
                onNewTracker =  {
                    navController.navigate("settings/-1")
                }
            )
        }
        composable(
            "settings/{trackerIndex}",
            arguments = listOf(navArgument("trackerIndex") { type = NavType.IntType }),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            },
        ) {
            val trackerIndex = it.arguments?.getInt("trackerIndex")
            val tracker = if (trackerIndex == null || trackerIndex == -1)
                null
            else
                ReflectRepo.model.getTracker(trackerIndex)
            SettingsScreen(tracker) {
                navController.navigate("home")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReflectTheme {
        HomeScreen()
    }
}