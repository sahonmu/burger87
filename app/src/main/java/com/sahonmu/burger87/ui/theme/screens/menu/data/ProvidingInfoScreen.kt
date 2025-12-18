package com.sahonmu.burger87.ui.theme.screens.menu.data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.enums.InfoMenu
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.utils.IntentUtils


@Preview(showBackground = true)
@Composable
fun ProvidingInfoScreenScreenPreview() {
    ProvidingInfoScreen(rememberNavController())
}


@Composable
fun ProvidingInfoScreen(
    navController: NavHostController,
) {

    val context = rememberUiState().context

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            title = InfoMenu.PROVIDING_INFO.menuName,
            onBack = { navController.popBackStack() }
        )
        Line(height = 1.dp, color = Gray_200)

        val emailRegex =
            Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")

        val text = "상점의 정보는 네이버 지도&구글 지도의 업체가 명시해 놓은 정보를 입력하였습니다." +
                "\n\n" +
                "상점 정보는 '업데이트'에 명시된 날짜에 입력 하였으며, 현재 정보와 상이할 수 있습니다." +
                "\n\n" +
                "사진의 경우 업체가 등록해놓은 사진의 URL을 참고하였으며, 폐점된 상점의 경우 제가 가지고 있는 사진을 등록하였습니다." +
                "\n\n" +
                "정보 변경이나 삭제가 필요한 경우 sahonmu@gmail.com로 연락 주시기 바랍니다"

        val annotatedString = buildAnnotatedString {
            append(text)

            emailRegex.findAll(text).forEach { match ->
                addStyle(
                    style = SpanStyle(
                        color = Color(0xFF1E88E5),
                        textDecoration = TextDecoration.Underline
                    ),
                    start = match.range.first,
                    end = match.range.last + 1
                )

                addStringAnnotation(
                    tag = "EMAIL",
                    annotation = match.value,
                    start = match.range.first,
                    end = match.range.last + 1
                )
            }
        }

        ClickableText(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 12.dp),
            text = annotatedString,
            onClick = { offset ->
                annotatedString
                    .getStringAnnotations("EMAIL", offset, offset)
                    .firstOrNull()
                    ?.let {
                        IntentUtils.startActivityReportData(context)
                    }
            }
        )
    }
}

