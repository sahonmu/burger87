package com.sahonmu.burger87.ui.theme.permission

import android.Manifest
import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Confirm
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.utils.IntentUtils

@Composable
fun LocationPermissionHandler(
    onPermissionGranted: () -> Unit,
    onPermissionDenied: () -> Unit
) {
    val context = rememberUiState().context
    val activity = context as Activity

    var showIntentPermission by remember { mutableStateOf(false) }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            when {
                fineGranted || coarseGranted -> {
                    onPermissionGranted()
                }

                // 영구 거부 (다시 묻지 않음)
                !ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                    showIntentPermission = true
                }

                else -> {
                    onPermissionDenied()
                }
            }
        }

    LaunchedEffect(Unit) {
        when {
            CheckPermission.hasLocationPermission(context) -> {
                onPermissionGranted()
            }
            else -> {
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    if(showIntentPermission) {
        Confirm(
            message = "위치 기능을 사용하려면\n위치권한 동의가 필요합니다.\n설정으로 이동하기",
            positiveButtonText = "이동하기",
            negativeButtonText = "취소",
            onPositive = {
                IntentUtils.startActivityOpenAppSetting(context)
                onPermissionDenied()
            },
            onDismissRequest = {
                onPermissionDenied()
            }
        )
    }
}
