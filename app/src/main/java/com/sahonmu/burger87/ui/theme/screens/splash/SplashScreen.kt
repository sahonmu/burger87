package com.sahonmu.burger87.ui.theme.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.navigate
import com.sahonmu.burger87.ui.theme.Base
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
) {
    var visibilityPermissionNotice by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(Constants.SPLASH_DURATION)
        navController.navigate(Screens.MAP)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .systemBarsPadding()
//            .navigationBarsPadding()
            .background(color = Base),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(width = 174.8.dp, height = 69.6.dp),
                painter = painterResource(R.drawable.splash_text),
                contentDescription = null
            )
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(R.drawable.splash_logo),
                contentDescription = null
            )
        }
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}

