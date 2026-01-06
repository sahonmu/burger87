package com.sahonmu.burger87.ui.theme.screens.announcement.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import domain.sahonmu.burger87.vo.announcement.Announcement

@Preview(showBackground = true)
@Composable
fun AnnouncementPreviewDetailBox() {
    AnnouncementDetailBox(
        modifier = Modifier.fillMaxSize(),
        announcement = Announcement(
            id = 0,
            createdAt = "",
            title = "버거왕오픈",
            contents = "버거왕이 새롭게 리뉴얼됐습니다",
            image = null,
            storeId = null,
            isHeader = false,
            link = null
        )
    )
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AnnouncementDetailBox(
    modifier: Modifier = Modifier,
    announcement: Announcement,
) {

    Column(
        modifier = modifier.padding(vertical = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            Text(
                text = announcement.title,
                color = Gray_900,
                fontSize = 15.sp
            )

            HeightMargin(10.dp)
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = announcement.createdAt.toYearMonthDay(),
                    color = Gray_600,
                    fontSize = 13.sp
                )
            }
            HeightMargin(10.dp)
        }
        Line(height = 1.dp, color = Gray_200)

        announcement.image?.let {
            GlideImage(
                modifier = Modifier.fillMaxWidth(),
                model = it,
                contentDescription = null
            ) {
                it.centerInside()
            }
        }

        HeightMargin(height = 30.dp)
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        ) {
            Text(
                text = announcement.contents,
                color = Gray_600,
                fontSize = 15.sp
            )
        }
    }

}