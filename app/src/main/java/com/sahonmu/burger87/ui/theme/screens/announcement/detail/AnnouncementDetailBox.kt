package com.sahonmu.burger87.ui.theme.screens.announcement.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import domain.sahonmu.burger87.vo.announcement.Announcement


@Composable
fun AnnouncementDetailBox(
    modifier: Modifier = Modifier,
    announcement: Announcement,
) {

    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(
            text = announcement.title,
            color = Gray_900,
            fontSize = 19.sp
        )

        HeightMargin(10.dp)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = announcement.createdAt.toYearMonthDay(),
                color = Gray_600,
                fontSize = 14.sp
            )
        }
        HeightMargin(10.dp)
        Line(height = 1.dp, color = Gray_200)

        HeightMargin(height = 30.dp)
        Text(
            text = announcement.contents,
            color = Gray_600,
            fontSize = 16.sp
        )
    }

}