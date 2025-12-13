@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.SearchTitle
import com.sahonmu.burger87.ui.theme.screens.components.TitleWithIncludeClosed
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber


@Preview
@Composable
fun StoreSearchScreenPreview() {
    StoreSearchScreen(
        navController = rememberNavController(),
        storeList = mutableListOf()
    )
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun StoreSearchScreen(
    navController: NavHostController,
    storeList: MutableList<Store>,
) {

    val uiState = rememberUiState()
    val scope = uiState.scope

    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeSearchUiState = storeViewModel.storeSearchUiState.collectAsState().value
    var input by rememberSaveable { mutableStateOf("") }

    var isLaunched by rememberSaveable { mutableStateOf(false) }
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        if (!isLaunched) {
            storeViewModel.searchByReset(storeList = storeList)
            isLaunched = true
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.isScrollInProgress }
            .collect { isScrolling ->
                if (isScrolling) {
                    uiState.keyboardController?.hide()
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .statusBarsPadding()
    ) {
        SearchTitle(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            onBack = { navController.popBackStack() },
            onKeyword = { keyword ->
                input = keyword
                if (keyword.isNotEmpty()) {
                    storeViewModel.searchByKeyword(keyword = keyword, storeList = storeList)
                } else {
                    storeViewModel.searchByReset(storeList = storeList)
                }
            }
        )

        Line(height = 1.dp, Gray_200)

        if (input.isNotEmpty() && storeSearchUiState.searchList.isNotEmpty()) {
            StoreSearchResultRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .padding(horizontal = 16.dp),
                resultCount = if (input.isEmpty()) 0 else storeSearchUiState.searchList.size,
            )

            Line(height = 1.dp, Gray_200)
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(storeSearchUiState.searchList) { index, item ->
                StoreSearchRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .padding(horizontal = 10.dp),
                    store = item,
                    keyword = input,
                    onClick = {
                        navController.navigate("${Screens.STORE_DETAIL}/${item.encode()}")
                    }
                )
                if (index != storeList.lastIndex) {
                    Line(height = 1.dp, Gray_200)
                }
            }
        }

    }
}