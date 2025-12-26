package com.sahonmu.burger87.ui.theme.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.enums.Transition
import com.sahonmu.burger87.extensions.enterTransition
import com.sahonmu.burger87.ui.theme.Burger87Theme
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Toast
import com.sahonmu.burger87.ui.theme.screens.main.NavGraph
import com.sahonmu.burger87.viewmodels.AppInfoViewModel
import com.sahonmu.burger87.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber


val LocalActivity = compositionLocalOf<ComponentActivity> {
    error("CompositionLocal LocalActivity not present")
}

@Composable
fun getActivity() = LocalContext.current as ComponentActivity

@Composable
inline fun <reified VM : ViewModel> composableActivityViewModel(
    key: String? = null,
    factory: ViewModelProvider.Factory? = null
): VM = viewModel(
    VM::class.java,
    getActivity(),
    key,
    factory
)


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Burger87Theme {

                val uiState = rememberUiState()
                val context = uiState.context
                val scope = uiState.scope

                val mainViewModel = composableActivityViewModel<MainViewModel>()
                val appInfoViewModel: AppInfoViewModel = hiltViewModel()

                val navController = rememberNavController()

                val appInfoUiState = appInfoViewModel.appInfoViewUiState.value
                if(appInfoUiState.appInfo.appVersion.isNotEmpty()) {
                    Timber.i("앱인포 = ${appInfoUiState.appInfo}")
//                    Toast.makeText(this, appInfoUiState.appInfo.forceUpdate.toString(), Toast.LENGTH_SHORT).show()
                }

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavGraph(navController = navController, screen = Screens.SPLASH)
                    }
                }

                LaunchedEffect(Unit) {
                    appInfoViewModel.requestAppInfo()
                }

                var showToast by rememberSaveable { mutableStateOf(false) }
                var toastMessage by rememberSaveable { mutableStateOf("") }

                AnimatedVisibility(
                    visible = showToast,
                    enter = Transition.SCALE_IN.enterTransition(duration = 100),
                    exit = ExitTransition.None
                ) {
                    Toast(
                        message = toastMessage
                    )
                    scope.launch {
                        delay(2000L)
                        showToast = false
                    }
                }

                LaunchedEffect(Unit) {
                    mainViewModel.toastData.collect { message ->
                        toastMessage = message
                        showToast = true
                    }
                }
            }
        }
    }
}