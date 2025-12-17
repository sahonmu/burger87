package com.sahonmu.burger87.ui.theme.screens.menu.statistics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.google.android.gms.maps.model.RoundCap
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.DataManager
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import domain.sahonmu.burger87.vo.store.Store


@Preview(showBackground = true)
@Composable
fun StatisticsListRowPreview() {

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        StatisticsListRow(
            modifier = Modifier
                .fillMaxWidth(),
            title = "타이틀",
            contents = "컨텐츠",
            painter = painterResource(R.drawable.emoji_sad_selected)
        )

        StatisticsListMultiRow(
            modifier = Modifier
                .fillMaxWidth(),
            title = "타이틀",
            contents = "컨텐츠",
            storeList = mutableListOf<Store>().apply {
                add(DataManager.store())
                add(DataManager.store())
                add(DataManager.store())
                add(DataManager.store())

            },
            painter = painterResource(R.drawable.emoji_sad_selected)
        )
    }

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


@Composable
fun StatisticsListMultiRow(
    modifier: Modifier = Modifier,
    title: String,
    contents: String,
    storeList: List<Store>,
    painter: Painter,
    backgroundColor: Color = Black,
    contentsColor: Color = Base,
    showBranch: Boolean = false
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

        HeightMargin(5.dp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                WidthMargin(10.dp)
            }
            items(storeList) { item ->
                Box(
                    modifier = Modifier
                        .border(width = 1.dp, color = contentsColor, shape = RoundedCornerShape(20.dp))
                        .background(color = backgroundColor, shape = RoundedCornerShape(20.dp))
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        text = if(showBranch) item.branch else item.fullName,
                        fontSize = 13.sp,
                        color = contentsColor
                    )
                }
            }
            item {
                WidthMargin(10.dp)
            }
        }
        HeightMargin(10.dp)

        Line(height = 1.dp, color = Gray_200)
    }
}
