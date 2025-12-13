package com.sahonmu.burger87.ui.theme.screens.menu.event.list

import androidx.compose.foundation.layout.Arrangement
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
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import domain.sahonmu.burger87.vo.event.Event


@Composable
fun EventListRow(
    modifier: Modifier = Modifier,
    event: Event,
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = event.title,
            color = Gray_900,
            fontSize = 15.sp
        )
        Box(modifier = Modifier.fillMaxWidth(), Alignment.CenterEnd) {
            Text(
                text = event.createdAt.toYearMonthDay(),
                color = Gray_600,
                fontSize = 13.sp
            )
        }
    }
}