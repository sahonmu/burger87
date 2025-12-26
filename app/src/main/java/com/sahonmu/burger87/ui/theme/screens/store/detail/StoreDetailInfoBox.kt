package com.sahonmu.burger87.ui.theme.screens.store.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.DataManager
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Gray_700
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber

@Composable
fun StoreDetailInfoBox(
    modifier: Modifier = Modifier,
    store: Store,
    onInstagram: () -> Unit = { },
    onCall: () -> Unit = { },
    onShare: () -> Unit = { },
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
    ) {

        Text(
            text = store.address,
            fontSize = 16.sp,
            color = Gray_700
        )

        HeightMargin(24.dp)

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconBorderBox(
                        painter = painterResource(R.drawable.ic_instagram),
                        onClick = { onInstagram() }
                    )
                    IconBorderBox(
                        painter = painterResource(R.drawable.ic_call),
                        onClick = { onCall() }
                    )
                    IconBorderBox(
                        painter = painterResource(R.drawable.ic_share),
                        onClick = { onShare() }
                    )

                }
            }
            Margin(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterEnd
            ) {

                val text = if (store.updateDate == null) store.createdAt else store.updateDate
                Timber.i("//////// ${store.createdAt} / ${store.lastVisitDate} / ${store.updateDate}")
                Text(
                    text = "업데이트 : ${text?.toYearMonthDay()}\n최근방문 : ${store.lastVisitDate.toYearMonthDay()}\n방문 횟수 : ${store.visitCount}회",
                    color = Gray_900,
                    fontSize = 14.sp,
                    textAlign = TextAlign.End,
                )
            }
        }


    }
}

@Composable
fun IconBorderBox(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit = { },
) {
    Card(
        modifier = modifier
            .size(30.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(15.dp)),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(width = 1.dp, color = Base),
        colors = CardDefaults.cardColors(
            containerColor = White
        )
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.size(18.dp),
                painter = painter,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Base)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StoreDetailInfoBoxPreview() {
    StoreDetailInfoBox(
        modifier = Modifier.fillMaxWidth(),
        store = DataManager.store(),
    )
}

