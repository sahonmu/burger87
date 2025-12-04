package com.sahonmu.burger87.ui.theme.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.ui.theme.Burger87Theme
import com.sahonmu.burger87.ui.theme.screens.main.NavGraph
import com.sahonmu.burger87.viewmodels.AppInfoViewModel
import com.sahonmu.burger87.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
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
            }
        }
    }
}