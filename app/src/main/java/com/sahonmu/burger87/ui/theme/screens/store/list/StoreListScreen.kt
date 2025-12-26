@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.enums.SortMenu
import com.sahonmu.burger87.enums.sortMenu
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.TitleWithIncludeClosed
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.ui.theme.screens.composableActivityViewModel
import com.sahonmu.burger87.viewmodels.StoreViewModel
import com.sahonmu.burger87.viewmodels.base.LocationViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


@Preview
@Composable
fun StoreListScreenPreview() {
    StoreListScreen(
        navController = rememberNavController(),
    )
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun StoreListScreen(
    navController: NavHostController,
) {

    val uiState = rememberUiState()
    val scope = uiState.scope

    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeListUiState = storeViewModel.storeListUiState.collectAsState().value

    val locationViewModel = composableActivityViewModel<LocationViewModel>()
    val locationUiState = locationViewModel.locationUiState.collectAsState().value

    var selectedSortMenu by rememberSaveable { mutableStateOf(SortMenu.BASIC) }
    var selectedFilterMenu by rememberSaveable { mutableStateOf(storeListUiState.selectedFilterMenu) }
    val sortMenuList = SortMenu.entries.toMutableList()
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        storeViewModel.requestStoreByStoreList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .statusBarsPadding()
    ) {
        TitleWithIncludeClosed(
            title = "버거 목록",
            onBack = { navController.popBackStack() },
            onCheck = { checked -> }
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

            if (locationViewModel.isEmptyLocation()) {
                sortMenuList.remove(SortMenu.DISTANCE)
            }

            items(sortMenuList) { item ->
                StoreListRoundBox(
                    text = item.sortName,
                    isSelect = selectedSortMenu.sortName == item.sortName,
                    onClick = {
                        scope.launch {
                            listState.scrollToItem(0)
                        }
                        selectedSortMenu = sortMenu(item.sortName)

                        if(selectedSortMenu == SortMenu.DISTANCE) {
                            storeViewModel.calculateList(
                                latitude = locationUiState.latitude,
                                longitude = locationUiState.longitude
                            )
                        } else {
                            storeViewModel.filterList(
                                selectedSortMenu = selectedSortMenu,
                            )
                        }
                    }
                )
            }

            item {
                WidthMargin(width = 10.dp)
            }
        }

        HeightMargin(height = 10.dp)

        if (selectedSortMenu.sortName != SortMenu.BASIC.sortName && selectedSortMenu.sortName != SortMenu.DISTANCE.sortName) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item {
                    WidthMargin(width = 10.dp)
                }
                items(storeListUiState.filterGroup.toList()) { item ->
                    val menu = when (selectedSortMenu) {
                        SortMenu.CITY -> item.first
                        SortMenu.SCORE -> "${item.first}점"
                        SortMenu.VISIT_COUNT -> "${item.first}회방문"
                        else -> ""
                    }
                    val size = item.second.size

                    StoreListRoundBox(
                        text = "${menu}(${size})",
                        isSelect = storeListUiState.selectedFilterMenu == item.first,
                        onClick = {
                            storeListUiState.selectedFilterMenu = item.first
                            when (selectedSortMenu) {
                                SortMenu.CITY -> {
                                    storeViewModel.filterCity(item.first)
                                }

                                SortMenu.SCORE -> {
                                    storeViewModel.filterScore(storeListUiState.selectedFilterMenu)
                                }

                                SortMenu.VISIT_COUNT -> {
                                    storeViewModel.filterVisitCount(storeListUiState.selectedFilterMenu)
                                }
                                else -> {
                                    storeViewModel.filterReset()
                                }
                            }
                            scope.launch {
                                listState.scrollToItem(0)
                            }
                        }
                    )
                }
                item {
                    WidthMargin(width = 10.dp)
                }
            }
            HeightMargin(height = 10.dp)
        }
        Line(height = 1.dp, color = Gray_200)

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(storeListUiState.displayList) { item ->
                StoreListRow(
                    store = item,
                    onClick = { navController.navigate("${Screens.STORE_DETAIL}/${item.encode()}") }
                )
                Line(height = 1.dp, color = Gray_200)
            }
        }
    }
}