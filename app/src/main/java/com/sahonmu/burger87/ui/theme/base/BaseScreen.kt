package com.sahonmu.burger87.ui.theme.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.screens.components.Alert
import com.sahonmu.burger87.ui.theme.screens.composableActivityViewModel
import com.sahonmu.burger87.viewmodels.MainViewModel
import com.sahonmu.burger87.viewmodels.base.BaseViewModel

@Preview
@Composable
fun BaseScreenPreview() {
    BaseScreen(
        content = {
            Text("테스트")
        },
        viewModel = BaseViewModel()
    )
}

@Composable
fun BaseScreen(
    content: @Composable () -> Unit,
    viewModel: BaseViewModel = hiltViewModel(),
    statusColor: Color = White,
) {

    val uiState = rememberUiState()
    val context = uiState.context

    val mainViewModel = composableActivityViewModel<MainViewModel>()

    var showAlert by rememberSaveable { mutableStateOf(false) }
    var alertMessage by rememberSaveable { mutableStateOf("") }

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = statusColor
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Surface {
            content()
        }
    }

    if (showAlert && alertMessage.isNotEmpty()) {
        Alert(
            message = alertMessage,
            onDismissRequest = {
                showAlert = false
                alertMessage = ""
            }
        )
    }


    LaunchedEffect(Unit) {
        viewModel.toast.collect { message ->
            mainViewModel.showToast(message)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.alert.collect { message ->
            showAlert = true
            alertMessage = message
        }
    }

}
