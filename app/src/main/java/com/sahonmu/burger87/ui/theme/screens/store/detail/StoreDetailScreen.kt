package com.sahonmu.burger87.ui.theme.screens.store.detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.SplashBackground
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.map.StoreMarker
import com.sahonmu.burger87.ui.theme.screens.map.SummaryCard
import com.sahonmu.burger87.ui.theme.screens.map.SummaryPager
import com.sahonmu.burger87.viewmodels.MapViewModel
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StoreDetailScreen(
    navController: NavHostController,
    store: Store,
    mapViewModel: MapViewModel = hiltViewModel()
) {


    val pagerState = rememberPagerState(pageCount = {
        5
    })

    Column (
        modifier = Modifier.fillMaxSize().systemBarsPadding().statusBarsPadding().background(color = Gray_50),
    ) {

        StoreDetailTitle(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            title = store.name,
            onBack = { navController.popBackStack() }
        )
        Line(
            height = 1.dp,
            color = Gray_200
        )

        HorizontalPager(
            modifier = Modifier.fillMaxWidth().height(250.dp),
            state = pagerState,
        ) { page ->
            GlideImage(
                modifier = Modifier.fillMaxSize(),
                model = store.thumbImage,
                contentDescription = null
            ) {
                it.centerCrop()
            }
        }

    }
}

//@Preview
//@Composable
//fun StoreDetailScreenPreview() {
//    StoreDetailScreen(rememberNavController())
//}

