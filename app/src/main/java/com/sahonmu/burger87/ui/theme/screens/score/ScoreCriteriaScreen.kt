package com.sahonmu.burger87.ui.theme.screens.score

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.map.ScoreBox


@Preview(showBackground = true)
@Composable
fun ScoreCriteriaScreenPreview() {
    ScoreCriteriaScreen(rememberNavController())
}


@Composable
fun ScoreCriteriaScreen(
    navController: NavHostController,
) {

    Column(
        modifier = Modifier.fillMaxSize()
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


        Column (
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ScoreBox(
                    score = 5.0f
                )
                Text(
                    text = "맛, 서비스, 분위기 등 모든 요소가 완벽함",
                    color = Black,
                    fontSize = 16.5.sp
                )
            }

            Line(height = 1.dp, color = Gray_200)
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ScoreBox(
                    score = 4.5f
                )
                Text(
                    text = "다른 사람에게도 추천할 만함 재방문 의사있음",
                    color = Black,
                    fontSize = 16.5.sp
                )
            }

            Line(height = 1.dp, color = Gray_200)
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ScoreBox(
                    score = 4.0f
                )
                Text(
                    text = "다시 방문하기 위해 해당 지역을 찾아갈 의향 있음",
                    color = Black,
                    fontSize = 16.5.sp
                )
            }


            Line(height = 1.dp, color = Gray_200)
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ScoreBox(
                    score = 3.5f
                )
                Text(
                    text = "그 동네에 갈 일이 있다면 재방문 의사 있음",
                    color = Black,
                    fontSize = 16.5.sp
                )
            }

            Line(height = 1.dp, color = Gray_200)
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ScoreBox(
                    score = 3.0f
                )
                Text(
                    text = "그 동네 갈 일이 있고 다른 대안이 없다면 재방문 가능",
                    color = Black,
                    fontSize = 16.5.sp
                )
            }

            Line(height = 1.dp, color = Gray_200)
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ScoreBox(
                    score = 2.5f
                )
                Text(
                    text = "특별한 이유가 없다면 재방문 의사 없음",
                    color = Black,
                    fontSize = 16.5.sp
                )
            }


        }




    }

}

