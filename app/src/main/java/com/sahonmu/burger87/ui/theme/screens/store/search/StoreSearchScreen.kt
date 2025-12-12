@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.SearchTitle
import com.sahonmu.burger87.ui.theme.screens.components.TitleWithIncludeClosed
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
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

    var includeClosedStore by rememberSaveable { mutableStateOf(storeSearchUiState.includeClosed) }
//    var checked by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .statusBarsPadding()
    ) {
        SearchTitle(
            modifier = Modifier.fillMaxWidth().height(56.dp),
            onBack = { navController.popBackStack() },
            onClear = { }
        )

        Line(height = 1.dp, Gray_200)

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "폐점 포함",
                    fontSize = 13.sp,
                    color = Base
                )
                Checkbox(
                    modifier = Modifier.size(30.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = Base
                    ),
                    checked = includeClosedStore,
                    onCheckedChange = {
                        includeClosedStore = it
                    }
                )
                WidthMargin(10.dp)
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) { }


    }
}