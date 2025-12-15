package com.sahonmu.burger87.ui.theme.screens.menu.statistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin


@Preview(showBackground = true)
@Composable
fun StatisticsListRowPreview() {
    StatisticsListRow(
        modifier = Modifier
            .fillMaxWidth(),
        title = "타이틀",
        contents = "컨텐츠",
        painter = painterResource(R.drawable.emoji_sad_selected)
    )
}


@Composable
fun StatisticsListRow(
    modifier: Modifier = Modifier,
    title: String,
    contents: String,
    painter: Painter,
    contentsColor: Color = Base
) {

    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(25.dp),
                painter = painter,
                contentDescription = null
            )
            WidthMargin(10.dp)
            Text(
                text = title,
                fontSize = 15.sp,
                color = Gray_900
            )

            Margin(modifier = Modifier.weight(1f))
            Text(
                text = contents,
                fontSize = 15.sp,
                color = contentsColor,
                textAlign = TextAlign.End,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 21.sp
                )
            )
        }
        Line(height = 1.dp, color = Gray_200)
    }

}

