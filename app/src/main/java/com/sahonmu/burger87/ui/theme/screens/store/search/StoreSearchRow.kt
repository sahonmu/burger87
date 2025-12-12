@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.TitleWithIncludeClosed
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.vo.store.Store


@Preview
@Composable
fun StoreSearchRowPreview() {
    StoreSearchRow(
        navController = rememberNavController(),
        storeList = mutableListOf()
    )
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun StoreSearchRow(
    navController: NavHostController,
    storeList: MutableList<Store>,
) {

    val uiState = rememberUiState()
    val scope = uiState.scope

    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeSearchUiState = storeViewModel.storeSearchUiState.collectAsState().value

    var includeClosedStore by rememberSaveable { mutableStateOf(storeSearchUiState.includeClosed) }

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
        Line(height = 1.dp, Gray_200)

        

    }
}