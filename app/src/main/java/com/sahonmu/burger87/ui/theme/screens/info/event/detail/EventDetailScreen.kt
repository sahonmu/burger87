package com.sahonmu.burger87.ui.theme.screens.info.event.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.enums.InfoMenu
import com.sahonmu.burger87.enums.LoadState
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.info.event.list.EventListRow
import com.sahonmu.burger87.viewmodels.EventViewModel
import domain.sahonmu.burger87.vo.event.Event


@Preview(showBackground = true)
@Composable
fun EventDetailScreenScreenPreview() {
//    EventDetailScreen(rememberNavController())
}


@Composable
fun EventDetailScreen(
    navController: NavHostController,
    event: Event
) {

//    val eventViewModel: EventViewModel = hiltViewModel()
//    val eventUiState = eventViewModel.eventUiState.collectAsState().value
//
//    LaunchedEffect(Unit) {
//        eventViewModel.requestEventList()
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            title = event.title,
            onBack = { navController.popBackStack() }
        )
        Line(height = 1.dp, color = Gray_200)
    }
}

