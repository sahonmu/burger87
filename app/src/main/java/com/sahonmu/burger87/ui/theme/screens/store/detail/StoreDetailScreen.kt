package com.sahonmu.burger87.ui.theme.screens.store.detail

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.StoreDetailTab
import com.sahonmu.burger87.extensions.copy
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.PagerIndicator
import com.sahonmu.burger87.utils.log.IntentUtils
import com.sahonmu.burger87.viewmodels.MapViewModel
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StoreDetailScreen(
    navController: NavHostController,
    store: Store,
) {
    val mapViewModel: MapViewModel = hiltViewModel()
    val storeImageUiState = mapViewModel.storeDetailUiState.collectAsState().value

    val pagerState = rememberPagerState(pageCount = {
        storeImageUiState.storeImageLst.size
    })

    val context = rememberUiState().context

    LaunchedEffect(Unit) {
        mapViewModel.requestStoreImageList(store.id)
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
                if (storeImageUiState.storeImageLst.isNotEmpty()) {
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
                                model = storeImageUiState.storeImageLst[page].image,
                                contentDescription = null
                            ) {
                                it.centerCrop()
                            }
                        }

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

            item {

                StoreDetailTab(
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    selectedTab = storeImageUiState.selectedTab.value
                ) { tab ->
                    storeImageUiState.selectedTab.value = tab
                }

                Line(
                    height = 1.dp,
                    color = Gray_200
                )
            }

            if(storeImageUiState.selectedTab.value == StoreDetailTab.INFO) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                    ) {
                        StoreDetailInfoRow(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(R.drawable.ic_address),
                            info = store.address,
                        ) {
                            store.address.copy(context)
                        }

                        StoreDetailInfoRow(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(R.drawable.ic_call),
                            info = store.tel,
                        ) {
                            IntentUtils.startActivityForDialog(context, store.tel)
                        }
                        StoreDetailInfoRow(
                            modifier = Modifier.fillMaxWidth(),
                            painter = painterResource(R.drawable.ic_instagram),
                            info = store.instagram,
                        ) {
                            IntentUtils.startActivityForInstagram(context, store.instagram)
                        }

                        if(store.onTheWay.isNotEmpty()) {
                            StoreDetailInfoRow(
                                modifier = Modifier.fillMaxWidth(),
                                painter = painterResource(R.drawable.ic_instagram),
                                info = store.onTheWay,
                            ) {

                            }
                        }
                    }

                }
            } else {
                item {  }
            }
        }
    }
}

@Preview
@Composable
fun StoreDetailScreenPreview() {
//    StoreDetailScreen(
//        rememberNavController(),
//
//    )
}

