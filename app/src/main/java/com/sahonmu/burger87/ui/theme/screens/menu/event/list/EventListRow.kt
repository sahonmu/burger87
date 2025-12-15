package com.sahonmu.burger87.ui.theme.screens.menu.event.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.enums.EventState
import com.sahonmu.burger87.enums.checkSchedule
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_400
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import domain.sahonmu.burger87.vo.event.Event


@Composable
fun EventListRow(
    modifier: Modifier = Modifier,
    event: Event,
) {
    val eventState = checkSchedule(
        startDate = event.startDate,
        endDate = event.endDate
    )

    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = event.title,
            color = Gray_900,
            fontSize = 15.sp
        )

        var date: String
        var color: Color
        when (eventState) {
            EventState.SCHEDULED -> {
                date = "${event.startDate.toYearMonthDay()}~"
                color = Base
            }
            EventState.FINISHED -> {
                date = "~${event.endDate.toYearMonthDay()}"
                color = Gray_400
            }
            else -> {
                date = "~${event.endDate.toYearMonthDay()}"
                color = Gray_600
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = date,
                color = Gray_600,
                fontSize = 13.sp
            )
        }


        Box(modifier = Modifier.fillMaxWidth(), Alignment.CenterEnd) {
            Box(
                modifier = Modifier.border(width = 1.dp, color = color, shape = RoundedCornerShape(3.dp))
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
                    text = eventState.state,
                    color = color,
                    fontSize = 11.sp
                )
            }
        }
    }
}