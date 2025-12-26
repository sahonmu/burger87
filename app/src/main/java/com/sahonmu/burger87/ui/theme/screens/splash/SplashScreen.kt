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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType.Companion.Confirm
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.findActivity
import com.sahonmu.burger87.extensions.navigate
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Confirm
import com.sahonmu.burger87.ui.theme.screens.composableActivityViewModel
import com.sahonmu.burger87.utils.AppUtils
import com.sahonmu.burger87.utils.AppUtils.getAppVersionCode
import com.sahonmu.burger87.utils.IntentUtils
import com.sahonmu.burger87.viewmodels.AppInfoViewModel
import com.sahonmu.burger87.viewmodels.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    appInfoViewModel: AppInfoViewModel = hiltViewModel()
) {

    val uiState = rememberUiState()
    val context = uiState.context

    var showAppForceUpdateConfirm by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        val versionCode = context.getAppVersionCode()
        appInfoViewModel.checkForceUpdate(versionCode = versionCode)
    }

    LaunchedEffect(Unit) {
        appInfoViewModel.forceUpdateData.collect { exits ->
            if(exits) {
                showAppForceUpdateConfirm = true
            } else {
                delay(Constants.SPLASH_DURATION)
                navController.navigate(Screens.MAP)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
    
    if(showAppForceUpdateConfirm) {
        Confirm(
            message = "최신업데이트가 필요합니다.\n업데이트 하러가기",
            positiveButtonText = "업데이트",
            negativeButtonText = "취소",
            onPositive = {
                IntentUtils.startActivityForGooglePlay(context)
            },
            onDismissRequest = {
                showAppForceUpdateConfirm = false
                context.findActivity().finish()
            }
        )
    }

}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}

