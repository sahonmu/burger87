package com.sahonmu.burger87.ui.theme.screens.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.navigate
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
) {
    var visibilityPermissionNotice by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(Constants.SPLASH_DURATION)
                navController.navigate(Screens.SPLASH)

    }

//    Box(
//        modifier = Modifier.fillMaxSize().background(Violet_500),
//        contentAlignment = Alignment.Center
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.splash_logo),
//            contentDescription = null
//        )
//    }
}
