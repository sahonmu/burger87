@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.ui.theme.screens.map.StoreListRow
import com.sahonmu.burger87.viewmodels.MapViewModel
import domain.sahonmu.burger87.vo.store.Store


@Preview
@Composable
fun StoreListScreenPreview() {
    StoreListScreen(
        navController = rememberNavController(),
        storeList = mutableListOf()
    )
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun StoreListScreen(
    navController: NavHostController,
    storeList: MutableList<Store>,
) {
    val mapViewModel: MapViewModel = hiltViewModel()
    val storeListUiState = mapViewModel.storeListUiState.collectAsState().value
    var selectedCity by remember { mutableStateOf("전체") }
    var selectedSort by remember { mutableStateOf("기본 정렬") }
    var includeClosedStore by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        mapViewModel.addAllStore(storeList)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .statusBarsPadding()
    ) {
        Title(
            title = "버거 목록",
            onBack = { navController.popBackStack() }
        )

        Line(height = 1.dp, color = Gray_200)

        HeightMargin(height = 10.dp)

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            item {
                WidthMargin(width = 10.dp)
            }
            item {
                StoreListRoundBox(
                    text = "전체",
                    isSelect = selectedCity == "전체",
                    onClick = {
                        selectedCity = "전체"
                        mapViewModel.filterCity(selectedCity)
                    }
                )
            }

            items(storeListUiState.cityGroup.toList()) { (key, value) ->
                StoreListRoundBox(
                    text = "${key}(${value.size})",
                    isSelect = selectedCity == key,
                    onClick = {
                        selectedCity = key
                        mapViewModel.filterCity(selectedCity)
                    }
                )
            }
        }

        HeightMargin(10.dp)

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            item {
                WidthMargin(width = 10.dp)
            }

            item {
                StoreListRoundBox(
                    text = "기본 정렬",
                    isSelect = selectedSort == "기본 정렬",
                    onClick = {
                        selectedSort = "기본 정렬"
                        mapViewModel.sortDefault(selectedCity)
                    }
                )
            }
            item {
                StoreListRoundBox(
                    text = "별점 정렬",
                    isSelect = selectedSort == "별점 정렬",
                    onClick = {
                        selectedSort = "별점 정렬"
                        mapViewModel.sortScore()
                    }
                )
            }
            item {
                StoreListRoundBox(
                    text = "가다나 정렬",
                    isSelect = selectedSort == "가다나 정렬",
                    onClick = {
                        selectedSort = "가다나 정렬"
                        mapViewModel.sortAbc()
                    }
                )
            }

//            item {
//                StoreListRoundBox(
//                    text = "폐점제외",
//                    isSelect = includeClosedStore,
//                    onClick = {
//                        includeClosedStore = !includeClosedStore
//                        mapViewModel.includeClosedStore(includeClosedStore)
//                    }
//                )
//            }
        }
        HeightMargin(height = 10.dp)
        Line(height = 1.dp, color = Gray_200)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(storeListUiState.sortList) { index, item ->
                StoreListRow(
                    store = item,
                    onClick = { navController.navigate("${Screens.STORE_DETAIL}/${item.encode()}") }
                )
                if (index != storeList.lastIndex) {
                    Line(height = 1.dp, color = Gray_200)
                }
            }
        }
    }
}




