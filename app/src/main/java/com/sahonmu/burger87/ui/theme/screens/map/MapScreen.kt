package com.sahonmu.burger87.ui.theme.screens.map

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.extensions.findActivity
import com.sahonmu.burger87.extensions.moveItem
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Alert
import com.sahonmu.burger87.viewmodels.MapViewModel
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.enums.isOperation

@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(rememberNavController())
}

@Composable
fun MapScreen(
    navController: NavHostController,
    storeViewModel: StoreViewModel = hiltViewModel()
) {

    val uiState = rememberUiState()
    val scope = uiState.scope
    val context = uiState.context

    val mapViewModel: MapViewModel = viewModel()
    val storeMapUiState = storeViewModel.storeMapUiState.collectAsState().value

    val pagerState = rememberPagerState(pageCount = {
        storeMapUiState.storeList.size
    })

    var showAlert by rememberSaveable { mutableStateOf(false) }
    var isCardVisible by rememberSaveable { mutableStateOf(true) }
    val cardHeight = 125.dp
    val offsetY by animateDpAsState(
        targetValue = if (isCardVisible) 0.dp else cardHeight,
        animationSpec = tween(durationMillis = 400)
    )

    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }

    LaunchedEffect(Unit) {
        storeViewModel.requestStoreList()
    }

    LaunchedEffect(pagerState, storeMapUiState.storeList) {
        snapshotFlow { pagerState.currentPage }.collect { position ->
            storeMapUiState.selectedIndex.value = position
            googleMap?.let {
                val store = storeMapUiState.storeList[position]
                val latLng = LatLng(store.latitude, store.longitude)
                it.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f))
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
            .background(color = White),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (storeMapUiState.loadState == LoadState.FINISHED) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    ClusterMapView(
                        storeMapUiState = storeMapUiState,
                        mapViewModel = mapViewModel,
                        onMarkerClick = { store ->
                            storeMapUiState.selectedIndex.value =
                                storeMapUiState.storeList.indexOfFirst { it.id == store.id }
                            pagerState.moveItem(
                                scope = scope,
                                animate = false,
                                page = storeMapUiState.selectedIndex.value
                            )
                            isCardVisible = true
                        },
                        onMapClick = {
                            isCardVisible = false
                        },
                        onGoogleMap = {
                            googleMap = it
                        }
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 15.dp)
                    ) {
                        SummaryPager(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp)
                                .offset(y = offsetY)
                                .align(Alignment.BottomCenter),
                            pagerState = pagerState,
                            storeList = storeMapUiState.storeList,
                            onClickStore = { store ->
                                if (store.storeState.isOperation()) {
                                    navController.navigate("${Screens.STORE_DETAIL}/${store.encode()}")
                                } else {
                                    showAlert = true
                                }
                            }
                        )
                    }


                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 20.dp, end = 20.dp),
                    ) {
                        MapFloatingButtonBox(
                            modifier = Modifier,
                            onMenu = { navController.navigate(Screens.MENU.route) },
                            onSearch = {
                                val encode =
                                    storeMapUiState.storeList.filter { it.storeState.isOperation() }
                                        .encode()
                                navController.navigate("${Screens.STORE_SEARCH}/${encode}")
                            },
                            onStoreList = {
                                val encode = storeMapUiState.storeList.encode()
                                navController.navigate("${Screens.STORE_LIST}/${encode}")
                            }
                        )
                    }
                }
            }
        }
    }

    if (showAlert) {
        Alert(
            message = "폐업된 점포입니다.",
            onDismissRequest = { showAlert = false }
        )
    }

    var backPressedTime by remember { mutableLongStateOf(0L) }
    BackHandler {
        if (System.currentTimeMillis() - backPressedTime <= 2000L) {
            // 앱 종료
            context.findActivity().finish()
        } else {
            Toast.makeText(context, "뒤로 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
        }
        backPressedTime = System.currentTimeMillis()
    }

}




