package com.sahonmu.burger87.ui.theme.screens.menu.score

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.map.ScoreBox
import com.sahonmu.burger87.viewmodels.ScoreInfoViewModel


@Preview(showBackground = true)
@Composable
fun BasedOnScoreScreenPreview() {
    BasedOnScoreScreen(rememberNavController())
}


@Composable
fun BasedOnScoreScreen(
    navController: NavHostController,
) {

    val scoreInfoViewModel: ScoreInfoViewModel = hiltViewModel()
    val scoreInfoUiState = scoreInfoViewModel.scoreInfoUiState.collectAsState().value

    LaunchedEffect(Unit) {
        scoreInfoViewModel.requestScoreInfoList()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            title = "점수 기준",
            onBack = { navController.popBackStack() }
        )
        Line(height = 1.dp, color = Gray_200)

        Box(

        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                text = "해당 점수는 제 개인적인 경험을 바탕으로,\n맛·친절도·기타 서비스 요소를 종합하여 평가한 것입니다.",
                color = Black
            )
        }

        Line(height = 1.dp, color = Gray_200)
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxWidth()
        ) {

            itemsIndexed(scoreInfoUiState.scoreInfOList) { index, item ->
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    ScoreBox(
                        score = item.score
                    )
                    HeightMargin(height = 3.dp)
                    Text(
                        text = item.description,
                        color = Black,
                        fontSize = 14.5.sp
                    )
                }

                if (index != scoreInfoUiState.scoreInfOList.lastIndex) {
                    Line(height = 1.dp, color = Gray_200)
                }
            }
        }
    }
}

