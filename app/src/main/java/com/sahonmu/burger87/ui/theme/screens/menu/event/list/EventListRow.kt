package com.sahonmu.burger87.ui.theme.screens.menu.event.list

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.enums.EventState
import com.sahonmu.burger87.enums.checkSchedule
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_400
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.fontPadding
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
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
            color = Gray_900
        }
    }

    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Box(
                modifier = Modifier.border(width = 1.dp, color = color, shape = RoundedCornerShape(3.dp)).height(20.dp)
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 5.dp).align(Alignment.Center),
                    text = eventState.state,
                    color = color,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    style = fontPadding
                )
            }
            Text(
                text = event.title,
                color = Gray_900,
                fontSize = 15.sp
            )

        }

        HeightMargin(height = 3.dp)
        Text(
            text = date,
            color = Gray_600,
            fontSize = 13.sp
        )
//        Box(modifier = Modifier.fillMaxWidth(), Alignment.CenterEnd) {
//            Box(
//                modifier = Modifier.border(width = 1.dp, color = color, shape = RoundedCornerShape(3.dp))
//            ) {
//                Text(
//                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
//                    text = eventState.state,
//                    color = color,
//                    fontSize = 11.sp
//                )
//            }
//        }
    }
}