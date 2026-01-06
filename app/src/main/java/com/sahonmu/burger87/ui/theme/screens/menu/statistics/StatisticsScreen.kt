package com.sahonmu.burger87.ui.theme.screens.menu.statistics

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.InfoMenu
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.composableActivityViewModel
import com.sahonmu.burger87.viewmodels.MainViewModel
import domain.sahonmu.burger87.enums.StoreState
import domain.sahonmu.burger87.vo.store.Store


@Preview(showBackground = true)
@Composable
fun StatisticsScreenPreview() {
    StatisticsScreen(rememberNavController())
}


@SuppressLint("InvalidColorHexValue")
@Composable
fun StatisticsScreen(
    navController: NavHostController,
) {

    val mainViewModel = composableActivityViewModel<MainViewModel>()
    val storeUiState = mainViewModel.storeUiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            title = InfoMenu.STATISTICS.menuName,
            onBack = { navController.popBackStack() }
        )
        Line(height = 1.dp, color = Gray_200)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                StatisticsListRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "총 방문 상점",
                    contents = "${storeUiState.displayList.size}회",
                    painter = painterResource(R.drawable.emoji_meh_selected),
                    contentsColor = Color(0xFFFFA36051)
                )


                val scoreSorted = storeUiState.displayList.groupBy { it.score }.toSortedMap(compareByDescending { it }).toList()
                val scoreFirst = scoreSorted[0]
                val scoreLast = scoreSorted[scoreSorted.lastIndex]
                StatisticsListMultiRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "최고점 상점",
                    contents = "${scoreFirst.first}점",
                    storeList = scoreFirst.second.sortedByDescending { it.lastVisitDate },
                    painter = painterResource(R.drawable.emoji_strring_selected),
                    backgroundColor = Color(0xFFFFFFC6E1),
                    contentsColor = Color(0xFFFFE6076C),
                    onStore = { navController.navigate("${Screens.STORE_DETAIL}/${it.encode()}") }
                )

                StatisticsListMultiRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "최하점 상점",
                    contents = "${scoreLast.first}점",
                    storeList = scoreLast.second.sortedByDescending { it.lastVisitDate },
                    painter = painterResource(R.drawable.emoji_scared_selected),
                    backgroundColor = Color(0xFFFFC1CFFF),
                    contentsColor = Color(0xFFFF495A93),
                    onStore = { navController.navigate("${Screens.STORE_DETAIL}/${it.encode()}") }
                )
            }
            item {

                val visitCountSorted = storeUiState.displayList.groupBy { it.visitCount }.toSortedMap(compareByDescending { it }).toList()
                val visitCountGold = visitCountSorted[0]
                val visitCountSilver = visitCountSorted[1]
                val visitCountBronze = visitCountSorted[2]

                val visitCountGroup = storeUiState.displayList.groupBy { it.name }.maxBy { it.value.size }
                StatisticsListMultiRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "최대 방문 브랜드",
                    contents = "${visitCountGroup.key}(${visitCountGroup.value.size}개 지점)",
                    storeList = visitCountGroup.value.sortedByDescending { it.lastVisitDate },
                    painter = painterResource(R.drawable.emoji_pleased_selected),
                    backgroundColor = Color(0xFFFFFFCCBC),
                    contentsColor = Color(0xFFFF8D2F45),
                    showBranch = true,
                    onStore = { navController.navigate("${Screens.STORE_DETAIL}/${it.encode()}") }
                )

                StatisticsListMultiRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "최대 방문 1위",
                    contents = "${visitCountGold.first}회",
                    storeList = visitCountGold.second.sortedByDescending { it.lastVisitDate },
                    painter = painterResource(R.drawable.emoji_pleased_selected),
                    backgroundColor = Color(0xFFFFFFCCBC),
                    contentsColor = Color(0xFFFF8D2F45),
                    onStore = { navController.navigate("${Screens.STORE_DETAIL}/${it.encode()}") }
                )

                StatisticsListMultiRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "최대 방문 2위",
                    contents = "${visitCountSilver.first}회",
                    storeList = visitCountSilver.second.sortedByDescending { it.lastVisitDate },
                    painter = painterResource(R.drawable.emoji_pleased_selected),
                    backgroundColor = Color(0xFFFFFFCCBC),
                    contentsColor = Color(0xFFFF8D2F45),
                    onStore = { navController.navigate("${Screens.STORE_DETAIL}/${it.encode()}") }
                )

                StatisticsListMultiRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "최대 방문 3위",
                    contents = "${visitCountBronze.first}회",
                    storeList = visitCountBronze.second.sortedByDescending { it.lastVisitDate },
                    painter = painterResource(R.drawable.emoji_pleased_selected),
                    backgroundColor = Color(0xFFFFFFCCBC),
                    contentsColor = Color(0xFFFF8D2F45),
                    onStore = { navController.navigate("${Screens.STORE_DETAIL}/${it.encode()}") }
                )

//                val visitLocation = storeUiState.displayList.groupBy { it.cityFilter }.toList()
//                    .sortedByDescending { it.second.size }
//                StatisticsListRow(
//                    modifier = Modifier.fillMaxWidth(),
//                    title = "최대 방문 지역",
//                    contents = "${visitLocation.first().first} ${visitLocation.first().second.size}회 방문",
//                    painter = painterResource(R.drawable.emoji_comfy_selected),
//                    contentsColor = Color(0xFFFFAB4C63)
//                )

                val visitLocation = storeUiState.displayList.groupBy { it.cityFilter }
                StatisticsListLocationMultiRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "최대 방문 지역",
                    contents = "",
                    visitLocation = visitLocation,
                    painter = painterResource(R.drawable.emoji_comfy_selected),
                    backgroundColor = Color(0xFFFFFED2AA),
                    contentsColor = Color(0xFFFFAB4C63),
                )

                val closedList = storeUiState.displayList.filter { it.storeState == StoreState.CLOSED }
                StatisticsListRow(
                    modifier = Modifier.fillMaxWidth(),
                    title = "폐점",
                    contents = "${closedList.size}개",
                    painter = painterResource(R.drawable.emoji_lonely_selected),
                    contentsColor = Color(0xFFFF3A7C92)
                )
            }
        }
    }
}

private fun visitCountText(list: List<Store>): String {
    if(list.isNotEmpty()) {
        if(list.size == 1) return "${list.first().fullName}/${list.first().visitCount}회"

        var text = ""
        list.forEachIndexed { index, store ->
            text += "${store.fullName}/${store.visitCount}회"
            if(index != list.lastIndex){
                text += "\n"
            }
        }
        return text
    }
    return ""
}


private fun scoreText(list: List<Store>): String {
    if(list.isNotEmpty()) {
        if(list.size == 1) return "${list.first().fullName}/${list.first().visitCount}회"

        var text = ""
        list.forEachIndexed { index, store ->
            text += "${store.fullName}/${store.score}점"
            if(index != list.lastIndex){
                text += "\n"
            }
        }
        return text
    }
    return ""
}

