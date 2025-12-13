@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.enums.SortMenu
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.TitleWithIncludeClosed
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.ui.theme.screens.map.StoreListRow
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.vo.store.Store
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.collections.component1
import kotlin.collections.component2


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

    val uiState = rememberUiState()
    val scope = uiState.scope

    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeListUiState = storeViewModel.storeListUiState.collectAsState().value

    var selectedCity by remember { mutableStateOf("") }
    var selectedScore by rememberSaveable { mutableStateOf(5.0f) }
    var selectedChar by rememberSaveable { mutableStateOf('a') }

    var includeClosedStore by rememberSaveable { mutableStateOf(true) }

    var selectedSortMenu by rememberSaveable { mutableStateOf(SortMenu.BASIC) }

    var scoreGroup by rememberSaveable { mutableStateOf(storeListUiState.scoreGroup) }

    LaunchedEffect(Unit) {
        if(storeListUiState.sortList.isNotEmpty()) {
            selectedScore = storeListUiState.sortList.maxBy { it.score }.score
        }

        if(storeListUiState.sortList.isNotEmpty()) {
            selectedCity = storeListUiState.sortList.groupBy { it.cityFilter }.toList().maxBy { it.second.size }.first
        }

        if(storeListUiState.sortList.isNotEmpty()) {
            selectedChar = storeListUiState.sortList.groupBy { it.name.first().uppercaseChar() }.toSortedMap().firstKey()
        }
    }

    val sortMenuList = SortMenu.entries.toMutableList()


    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        storeViewModel.addAllStore(storeList)
    }


    val flatScoreList = remember(storeListUiState.scoreGroup) {
        storeListUiState.scoreGroup.flatMap { (key, list) ->
            list.map { item -> key to item }
        }
    }

    val flatCityList = remember(storeListUiState.cityGroup) {
        storeListUiState.cityGroup.flatMap { (key, list) ->
            list.map { item -> key to item }
        }
    }

    val flatCharList = remember(storeListUiState.charGroup) {
        storeListUiState.charGroup.flatMap { (key, list) ->
            list.map { item -> key to item }
        }
    }

    LaunchedEffect(listState, flatScoreList, flatCityList, flatCharList) {
        snapshotFlow { listState.firstVisibleItemIndex }
            .collect { index ->
                if (selectedSortMenu == SortMenu.CITY) {
                    val stickyIndex = storeListUiState.cityGroup.keys.toList().indexOf(flatCityList[index].second.cityFilter)
                    flatCityList.getOrNull(index - stickyIndex)?.let { item ->
                        selectedCity = item.first
                    }
                } else if (selectedSortMenu == SortMenu.SCORE) {
                    val stickyIndex = storeListUiState.scoreGroup.keys.toList().indexOf(flatScoreList[index].second.score)
                    flatScoreList.getOrNull(index - stickyIndex)?.let { item ->
                        selectedScore = item.first
                    }

                } else if (selectedSortMenu == SortMenu.CHAR) {
                    var name = flatCharList[index].second.name
                    val firstText = name.first().uppercaseChar()
                    val firstChar = storeViewModel.getChosung(firstText)
                    val stickyIndex = storeListUiState.charGroup.keys.toList().indexOf(firstChar)
                    flatCharList.getOrNull(index - stickyIndex)?.let { item ->
                        Timber.i("이런시팔 ${storeViewModel.getChosung(item.second.name.first().uppercaseChar())} / ${item.second.name} / $index - $stickyIndex /${index - stickyIndex}")
                        selectedChar = storeViewModel.getChosung(item.second.name.first().uppercaseChar())
                    }
                }
            }
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
            onCheck = { checked ->
                includeClosedStore = checked
                storeViewModel.includeClosedStore(includeClosedStore)
            }
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

            items(sortMenuList) { item ->
                StoreListRoundBox(
                    text = item.sortName,
                    isSelect = selectedSortMenu.sortName == item.sortName,
                    onClick = {
                        scope.launch {
                            listState.scrollToItem(0)
                        }
                        selectedSortMenu = when (item.sortName) {
                            SortMenu.BASIC.sortName -> {
                                SortMenu.BASIC
                            }
                            SortMenu.CITY.sortName -> {
                                SortMenu.CITY
                            }
                            SortMenu.SCORE.sortName -> {
                                SortMenu.SCORE
                            }
                            else -> {
                                SortMenu.CHAR
                            }
                        }
                    }
                )
            }
        }

        if (selectedSortMenu == SortMenu.CITY) {
            HeightMargin(height = 10.dp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item {
                    WidthMargin(width = 10.dp)
                }

                items(storeListUiState.cityGroup.toList().sortedByDescending { it.second.size }) { (key, value) ->
                    StoreListRoundBox(
                        text = "${key}(${value.size})",
                        isSelect = selectedCity == key,
                        onClick = {
                            val stickyIndex = storeListUiState.cityGroup.keys.toList().indexOf(key)
                            selectedCity = key
                            val index = flatCityList.indexOfFirst { it.first == key } + stickyIndex
                            scope.launch {
                                listState.scrollToItem(index)
                            }
                        }
                    )
                }
            }
        } else if(selectedSortMenu == SortMenu.SCORE) {
            HeightMargin(height = 10.dp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item {
                    WidthMargin(width = 10.dp)
                }

                items(storeListUiState.scoreGroup.toList().sortedByDescending { it.first }) { (key, value) ->
                    StoreListRoundBox(
                        text = "${key}점(${value.size})",
                        isSelect = selectedScore == key,
                        onClick = {
                            val stickyIndex = storeListUiState.scoreGroup.keys.toList().indexOf(key)
                            selectedScore = key
                            val index = flatScoreList.indexOfFirst { it.first == key } + stickyIndex
                            scope.launch {
                                listState.scrollToItem(index)
                            }
                        }
                    )
                }
            }
        } else if(selectedSortMenu == SortMenu.CHAR) {
            HeightMargin(height = 10.dp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                state = rememberLazyListState(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                item {
                    WidthMargin(width = 10.dp)
                }

                items(storeListUiState.charGroup.toList().sortedBy { it.first }) { (key, value) ->
                    StoreListRoundBox(
                        text = "${key}(${value.size})",
                        isSelect = selectedChar == key,
                        onClick = {
                            selectedChar = key
                        }
                    )
                }
            }

        }
        HeightMargin(height = 10.dp)
        Line(height = 1.dp, color = Gray_200)

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {

            if(selectedSortMenu == SortMenu.CITY) {
                storeListUiState.cityGroup.forEach { (cityName, list) ->
                    stickyHeader {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(White),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = cityName,
                                    fontSize = 19.sp,
                                    color = Base
                                )
                            }
                            Line(height = 1.dp, color = Gray_200)
                        }
                    }

                    itemsIndexed(list) { index, item ->
                        StoreListRow(
                            store = item,
                            onClick = { navController.navigate("${Screens.STORE_DETAIL}/${item.encode()}") }
                        )
                        if (index != storeList.lastIndex) {
                            Line(height = 1.dp, color = Gray_200)
                        }
                    }
                }
            } else if(selectedSortMenu == SortMenu.SCORE) {
                storeListUiState.scoreGroup.forEach { (score, list) ->
                    stickyHeader {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(White),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = score.toString(),
                                    fontSize = 19.sp,
                                    color = Base
                                )
                            }
                            Line(height = 1.dp, color = Gray_200)
                        }
                    }

                    itemsIndexed(list) { index, item ->
                        StoreListRow(
                            store = item,
                            onClick = { navController.navigate("${Screens.STORE_DETAIL}/${item.encode()}") }
                        )
                        if (index != storeList.lastIndex) {
                            Line(height = 1.dp, color = Gray_200)
                        }
                    }
                }
            } else if(selectedSortMenu == SortMenu.CHAR) {
                storeListUiState.charGroup.toList().sortedBy { it.first }.forEach { (char, list) ->
                    stickyHeader {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(48.dp)
                                    .background(White),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = char.toString(),
                                    fontSize = 19.sp,
                                    color = Base
                                )
                            }
                            Line(height = 1.dp, color = Gray_200)
                        }
                    }

                    itemsIndexed(list) { index, item ->
                        StoreListRow(
                            store = item,
                            onClick = { navController.navigate("${Screens.STORE_DETAIL}/${item.encode()}") }
                        )
                        if (index != storeList.lastIndex) {
                            Line(height = 1.dp, color = Gray_200)
                        }
                    }
                }
            } else {
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
}