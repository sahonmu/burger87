package com.sahonmu.burger87.ui.theme.screens.announcement.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.enums.InfoMenu
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.viewmodels.AnnouncementViewModel

@Preview(showBackground = true)
@Composable
fun AnnouncementListScreenPreview() {
    AnnouncementListScreen(
        navController = rememberNavController(),
    )
}

@Composable
fun AnnouncementListScreen(
    navController: NavHostController
) {

    val announcementViewModel: AnnouncementViewModel = hiltViewModel()
    val announcementUiState = announcementViewModel.announcementListUiState.collectAsState().value

    LaunchedEffect(Unit) {
        announcementViewModel.requestAnnouncementList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            title = "공지사항",
            onBack = { navController.popBackStack() }
        )

        Line(height = 1.dp, color = Gray_200)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(announcementUiState.announcementList) { index, item ->
                AnnouncementListRow(
                    modifier = Modifier.fillMaxWidth(),
                    announcement = item,
                    onClick = {
                        navController.navigate("${Screens.ANNOUNCEMENT_DETAIL}/${item.encode()}")
                    }
                )
                if(index != announcementUiState.announcementList.lastIndex) {
                    Line(height = 1.dp, color = Gray_200)
                }
            }
        }
    }
}