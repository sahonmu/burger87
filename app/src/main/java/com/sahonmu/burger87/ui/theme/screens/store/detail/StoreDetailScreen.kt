@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.common.DataManager
import com.sahonmu.burger87.enums.MapApp
import com.sahonmu.burger87.enums.RouteType
import com.sahonmu.burger87.enums.getInstalledMapApps
import com.sahonmu.burger87.enums.openRoute
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.SampleBottomSheet
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Alert
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.PagerIndicator
import com.sahonmu.burger87.ui.theme.sheet.MapAppList
import com.sahonmu.burger87.utils.IntentUtils
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.enums.storeState
import domain.sahonmu.burger87.vo.store.Store

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun StoreDetailScreen(
    navController: NavHostController,
    store: Store,
) {
    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeDetailUiState = storeViewModel.storeDetailUiState.collectAsState().value

    val pagerState = rememberPagerState(pageCount = {
        storeDetailUiState.storeImageLst.size
    })

    val context = rememberUiState().context

    var showAlert by rememberSaveable { mutableStateOf(false) }
    var showAlertMessage by rememberSaveable { mutableStateOf("") }

    var showSheet by rememberSaveable { mutableStateOf(false) }
    var routeType by rememberSaveable { mutableStateOf(RouteType.Car) }

    LaunchedEffect(Unit) {
        storeViewModel.requestStoreImageList(store.id)
        storeViewModel.requestStoreMenuList(store.id)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .statusBarsPadding()
            .background(color = Gray_50),
    ) {

        StoreDetailTitle(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            title = store.name,
            branch = store.branch,
            score = store.score,
            state = store.state.storeState(),
            onBack = { navController.popBackStack() }
        )
        Line(
            height = 1.dp,
            color = Gray_200
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            item {
                if (storeDetailUiState.storeImageLst.isNotEmpty()) {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                    ) {
                        val (pager, dot) = createRefs()
                        HorizontalPager(
                            modifier = Modifier
                                .fillMaxSize()
                                .constrainAs(pager) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                },
                            state = pagerState,
                        ) { page ->
                            GlideImage(
                                modifier = Modifier.fillMaxSize(),
                                model = storeDetailUiState.storeImageLst[page].image,
                                contentDescription = null
                            ) {
                                it.centerCrop()
                            }
                        }

                        if (storeDetailUiState.storeImageLst.size >= 2) {
                            PagerIndicator(
                                modifier = Modifier.constrainAs(dot) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom, margin = 12.dp)
                                },
                                pagerState = pagerState,
                                dotSize = 10.dp,
                                padding = 4.dp
                            )
                        }
                    }
                }
            }

            stickyHeader {
                StoreDetailInfoBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(White),
                    store = store,
                    onInstagram = {
                        IntentUtils.startActivityForInstagram(
                            context,
                            store.instagram
                        )
                    },
                    onCall = { IntentUtils.startActivityForDialog(context, store.tel) },
                    onShare = {
                        val name =
                            if (store.branch.isEmpty()) store.name else "${store.name}(${store.branch})"
                        val instagram = store.instagram.ifEmpty { "" }
                        IntentUtils.startActivityForShare(
                            context,
                            "${name}\n${store.address}\n${store.tel}\n${instagram}"
                        )
                    },
                    onRoute = { type ->
                        routeType = type
                        showSheet = true
                    }
                )

                Line(
                    height = 1.dp,
                    color = Gray_200
                )

                if(storeDetailUiState.storeMenuList.isNotEmpty()) {
                    StoreDetailTab(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(White),
                        selectedTab = storeDetailUiState.selectedTab.value
                    ) { tab ->
                        storeDetailUiState.selectedTab.value = tab
                    }

                    Line(
                        height = 1.dp,
                        color = Gray_200
                    )
                }
            }

            if (storeDetailUiState.storeMenuList.isNotEmpty()) {
                itemsIndexed(storeDetailUiState.storeMenuList) { index, item ->
                    StoreDetailMenuRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        storeMenu = item
                    ) {
                        showAlertMessage = item.description.ifEmpty { "정보 없음" }
                        showAlert = true
                    }
                    if (index != storeDetailUiState.storeMenuList.lastIndex) {
                        Line(
                            height = 1.dp, Gray_200
                        )
                    }
                }
            }
        }
    }

    if (showAlert) {
        Alert(
            message = showAlertMessage
        ) {
            showAlertMessage = ""
            showAlert = false
        }
    }

    SampleBottomSheet(
        show = showSheet,
        onDismiss = { showSheet = false },
        content = {
            val list = getInstalledMapApps(context).toMutableList()
            val routeForTransportation = routeType == RouteType.Transportation
            if(routeForTransportation) {
                list.remove(MapApp.TMAP)
            }
            if(list.isNotEmpty()) {
                MapAppList(
                    title = if(routeForTransportation) "대중교통 안내" else "자동차 안내" ,
                    list = list,
                    onClick = { mapApp ->
                        showSheet = false
                        openRoute(
                            context = context,
                            routeType = routeType,
                            app = mapApp,
                            lat = store.latitude,
                            lng = store.longitude,
                            name = store.fullName
                        )
                    }
                )
            } else {
                showAlert = true
                showAlertMessage = "설치된 지도 앱이 없습니다.\n(구글지도, 네이버지도, 카카오지도, 티맵)"
                showSheet = false
            }
        }
    )
}

@Preview
@Composable
fun StoreDetailScreenPreview() {
    StoreDetailScreen(
        navController = rememberNavController(),
        store = DataManager.store()
    )
}

