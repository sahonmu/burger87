package com.sahonmu.burger87.ui.theme.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.ui.theme.Burger87Theme
import com.sahonmu.burger87.ui.theme.screens.main.NavGraph
import com.sahonmu.burger87.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


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

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        NavGraph(navController = navController, screen = Screens.SPLASH)
                    }
                }

            }
        }
    }
}