package com.sahonmu.burger87.ui.theme.screens.info.event.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
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
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.viewmodels.EventViewModel


@Preview(showBackground = true)
@Composable
fun EventListScreenScreenPreview() {
    EventListScreen(rememberNavController())
}


@Composable
fun EventListScreen(
    navController: NavHostController,
) {

    val eventViewModel: EventViewModel = hiltViewModel()
    val eventUiState = eventViewModel.eventUiState.collectAsState().value

    LaunchedEffect(Unit) {
        eventViewModel.requestEventList()
    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding()
        ) {
            Title(
                title = InfoMenu.SHARING_EVENT_INFO.menuName,
                onBack = { navController.popBackStack() }
            )
            Line(height = 1.dp, color = Gray_200)

            if (eventUiState.loadState == LoadState.FINISHED) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(eventUiState.eventList) { index, item ->
                        EventListRow(
                            modifier = Modifier.clickable { navController.navigate("${Screens.EVENT_DETAIL}/${item.encode()}")},
                            event = item,
                        )

                        if (index != eventUiState.eventList.lastIndex) {
                            Line(height = 1.dp, color = Gray_200)
                        }
                    }
                }

            } else if (eventUiState.loadState == LoadState.EMPTY) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "이벤트 정보가 없습니다.",
                        fontSize = 17.sp,
                        color = Gray_900
                    )
                }
            }
        }
    }
}

