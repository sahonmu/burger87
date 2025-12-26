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
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.utils.IntentUtils

@Composable
fun LocationPermissionHandler(
    onPermissionGranted: () -> Unit
) {
    val context = rememberUiState().context
    val activity = context as Activity

    var permissionDenied by remember { mutableStateOf(false) }
    var permanentlyDenied by remember { mutableStateOf(false) }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            val fineGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
            val coarseGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

            when {
                fineGranted || coarseGranted -> {
                    permissionDenied = false
                    permanentlyDenied = false
                    onPermissionGranted()
                }

                // 영구 거부 (다시 묻지 않음)
                !ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                    permanentlyDenied = true
                }

                else -> {
                    permissionDenied = true
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

    when {
        permissionDenied -> {
            PermissionDeniedUI(
                onRetry = {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            )
        }

        permanentlyDenied -> {
            PermissionPermanentlyDeniedUI(
                onGoToSettings = {
                    IntentUtils.startActivityOpenAppSetting(context)
                }
            )
        }
    }
}

@Composable
fun PermissionDeniedUI(onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("위치 권한이 필요합니다")
        HeightMargin(height = 12.dp)
        Button(onClick = onRetry) {
            Text("다시 요청")
        }
    }
}

@Composable
fun PermissionPermanentlyDeniedUI(onGoToSettings: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("설정에서 위치 권한을 허용해주세요")
        HeightMargin(height = 12.dp)
        Button(onClick = onGoToSettings) {
            Text("설정으로 이동")
        }
    }
}