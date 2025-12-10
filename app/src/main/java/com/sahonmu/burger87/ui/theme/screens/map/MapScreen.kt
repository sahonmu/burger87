package com.sahonmu.burger87.ui.theme.screens.map

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.extensions.findActivity
import com.sahonmu.burger87.extensions.moveItem
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.viewmodels.MapViewModel
import com.sahonmu.burger87.viewmodels.ScoreInfoViewModel
import timber.log.Timber


@Preview
@Composable
fun MapScreenPreview() {
    MapScreen(rememberNavController())
}


@Composable
fun MapScreen(
    navController: NavHostController,
    mapViewModel: MapViewModel = hiltViewModel()
) {

    val uiState = rememberUiState()
    val scope = uiState.scope
    val context = uiState.context

    val mapViewUiState = mapViewModel.mapViewUiState.collectAsState().value

    val pagerState = rememberPagerState(pageCount = {
        mapViewUiState.storeList.size
    })


    LaunchedEffect(Unit) {
        mapViewModel.requestStoreList()
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { position ->
            mapViewUiState.selectedIndex.value = position
            Timber.i("selectedIndex pagerState = ${mapViewUiState.selectedIndex.value}")
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
            if (mapViewUiState.loadState == LoadState.LOADING) {
                Text(
                    text = "로딩중"
                )
            } else {

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    ClusterMapView(
                        mapViewUiState = mapViewUiState,
                        onMarkerClick = { store ->
                            mapViewUiState.selectedIndex.value = mapViewUiState.storeList.indexOfFirst { it.id == store.id }
                            pagerState.moveItem(
                                scope = scope,
                                animate = false,
                                page = mapViewUiState.selectedIndex.value
                            )
                            Timber.i("selectedIndex onMarkerClick = ${mapViewUiState.selectedIndex.value}")
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
                                .align(Alignment.BottomCenter),
                            pagerState = pagerState,
                            storeList = mapViewUiState.storeList,
                            onClickStore = { store ->
                                navController.navigate("${Screens.STORE_DETAIL}/${store.encode()}")
                            }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 20.dp, start = 20.dp),
                    ) {
                        RoundButton(
                            modifier = Modifier
                                .size(36.dp),
                            imageSize = 16.dp,
                            painter = painterResource(id = R.drawable.ic_icon_list),
                            onClick = { navController.navigate(Screens.INFO.route) }
                        )
                    }


                    Column(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(top = 20.dp, end = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        RoundButton(
                            modifier = Modifier
                                .size(36.dp),
                            painter = painterResource(id = R.drawable.ic_icon_store),
                            imageSize = 24.dp,
                            onClick = {
                                val encode = mapViewUiState.storeList.encode()
                                navController.navigate("${Screens.STORE_LIST}/${encode}")
                            }
                        )

                        RoundButton(
                            modifier = Modifier
                                .size(36.dp),
                            imageSize = 24.dp,
                            painter = painterResource(id = R.drawable.ic_star),
                            borderColor = Color(0xFFFFD700),
                            onClick = { navController.navigate(Screens.SCORE_CRITERIA.route) }
                        )
                    }
                }
            }
        }

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

