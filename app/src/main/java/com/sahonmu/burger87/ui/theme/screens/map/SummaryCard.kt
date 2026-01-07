package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.DataManager
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.fontPadding
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.vo.store.Store


@Preview(showBackground = true)
@Composable
fun SummaryCardPreview() {
    SummaryCard(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        store = DataManager.store()
    )
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun SummaryCard(
    modifier: Modifier = Modifier,
    store: Store,
    onCarRoute: () -> Unit = { },
    onTransportationRoute: () -> Unit = { },
    onClick: (Store) -> Unit = { }
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        onClick = {
            onClick(store)
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.size(110.dp)) {
                    GlideImage(
                        modifier = Modifier.fillMaxSize(),
                        model = store.thumbImage,
                        contentDescription = null
                    ) {
                        it.centerCrop()
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp, top = 6.dp, bottom = 6.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    Text(
                        text = if (store.branch.isEmpty()) store.name else "${store.name}(${store.branch})",
                        fontSize = 15.sp,
                        style = fontPadding
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "방문횟수 : ${store.visitCount}회\n최근방문 : ${store.lastVisitDate.toYearMonthDay()}",
                            fontSize = 10.5.sp,
                            maxLines = 2,
                            style = fontPadding
                        )
                        Margin(modifier = Modifier.weight(1f))
                        RoundButton(
                            modifier = Modifier
                                .size(28.dp)
                                .align(Alignment.Bottom),
                            painter = painterResource(id = R.drawable.ic_car_route),
                            imageSize = 19.dp,
                            color = White,
                            onClick = { onCarRoute() }
                        )
                        WidthMargin(width = 8.dp)
                        RoundButton(
                            modifier = Modifier
                                .size(28.dp)
                                .align(Alignment.Bottom),
                            painter = painterResource(id = R.drawable.ic_transportation_route),
                            imageSize = 14.5.dp,
                            color = White,
                            onClick = { onTransportationRoute() }
                        )
                    }
                }
            }

            if (!store.storeState.isOperation()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x77E7E7ED))
                )
            }
        }
    }
}


@Composable
fun SummaryPager(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    storeList: MutableList<Store>,
    onCarRoute: () -> Unit = { },
    onTransportationRoute: () -> Unit = { },
    onClickStore: (Store) -> Unit = { },
) {
    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSpacing = 10.dp,
        contentPadding = PaddingValues(horizontal = 15.dp)
    ) { page ->

        val store = storeList[page]
        SummaryCard(
            modifier = Modifier.fillMaxSize(),
            store = storeList[page],
            onCarRoute = { onCarRoute() },
            onTransportationRoute = { onTransportationRoute() },
            onClick = { onClickStore(store) }
        )
    }
}
