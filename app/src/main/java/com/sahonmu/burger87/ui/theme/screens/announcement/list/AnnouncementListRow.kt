package com.sahonmu.burger87.ui.theme.screens.announcement.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_400
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import domain.sahonmu.burger87.vo.announcement.Announcement

@Preview(showBackground = true)
@Composable
fun AnnouncementListRowPreview() {
//    AnnouncementListRow(
//        modifier = Modifier.fillMaxWidth(),
//        announcement = Announcement(
//            id = 0,
//            createdAt = "2025-12-04 04:36:52.012113+00",
//            title = "버거왕 오픈",
//            contents = "컨텐츠입니다",
//            image = null
//        )
//    )
}

@Composable
fun AnnouncementListRow(
    modifier: Modifier = Modifier,
    announcement: Announcement,
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if(announcement.isHeader) {
                Image(
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(color = Gray_400),
                    painter = painterResource(R.drawable.ic_header_fix),
                    contentDescription = null
                )
            }

            Text(
                text = announcement.title,
                color = Gray_900,
                fontSize = 15.sp
            )
        }

        Box(modifier = Modifier.fillMaxWidth(), Alignment.CenterEnd) {
            Text(
                text = announcement.createdAt.toYearMonthDay(),
                color = Gray_600,
                fontSize = 13.sp
            )
        }
    }
}