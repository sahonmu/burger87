@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
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
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.SearchTitle
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.vo.store.Store


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
            uiState.keyboardController?.show()
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

    Surface {
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
                            .clickable { navController.navigate("${Screens.STORE_DETAIL}/${item.encode()}") },
                        store = item,
                        keyword = input,
                    )
                    if (index != storeList.lastIndex) {
                        Line(height = 1.dp, Gray_200)
                    }
                }
            }
        }
    }
}