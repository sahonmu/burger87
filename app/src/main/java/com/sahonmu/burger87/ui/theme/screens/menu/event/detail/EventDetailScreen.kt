package com.sahonmu.burger87.ui.theme.screens.menu.event.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
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

