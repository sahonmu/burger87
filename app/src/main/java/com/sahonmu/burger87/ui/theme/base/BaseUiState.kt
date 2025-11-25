package com.sahonmu.burger87.ui.theme.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.CoroutineScope

@Stable
open class BaseUiState(
    val context: Context,
    val scope: CoroutineScope,
    val lifecycle: Lifecycle,
    val lifecycleOwner: LifecycleOwner,
    val keyboardController: SoftwareKeyboardController?
)

@Composable
fun rememberUiState(
    context: Context = LocalContext.current,
    scope: CoroutineScope = rememberCoroutineScope(),
    lifecycle: Lifecycle = LocalLifecycleOwner.current.lifecycle,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
): BaseUiState = remember(context, scope, lifecycle, lifecycleOwner) {
    BaseUiState(
        context = context,
        scope = scope,
        lifecycle = lifecycle,
        lifecycleOwner = lifecycleOwner,
        keyboardController = keyboardController
    )
}