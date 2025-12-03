package com.sahonmu.burger87.ui.theme.screens.map

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Score
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.fontPadding
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import domain.sahonmu.burger87.vo.store.Store

@Composable
@Preview
fun Preview() {

}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun SummaryCard(
    modifier: Modifier = Modifier,
    store: Store,
    onClick: (Store) -> Unit = { }
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        onClick = { onClick(store) }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),

                ) {
                GlideImage(
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp),
                    model = store.thumbImage,
                    contentDescription = null
                ) {
                    it.centerCrop()
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 12.dp, top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Bottom)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Text(
                            text = if (store.branch.isEmpty()) store.name else "${store.name}(${store.branch}점)",
                            fontSize = 17.sp,
                            style = fontPadding
                        )
                        Text(
                            text = "(★${store.score})",
                            fontSize = 12.sp,
                            color = Score,
                            style = fontPadding
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = store.address,
                            fontSize = 13.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            style = fontPadding
                        )
                    }
                }
            }


            RoundButton(
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.TopEnd)
                    .padding(end = 20.dp, top = 20.dp),
                painter = painterResource(R.drawable.splash_logo),
                round = 15.dp,
                onClick = { }
            )
        }
    }
}

@Composable
fun SummaryPager(
    modifier: Modifier = Modifier,
    storeList: MutableList<Store>,
    onSelectStore: (Store) -> Unit = { },
    onClickStore: (Store) -> Unit = { },
) {
    val pagerState = rememberPagerState(pageCount = {
        storeList.size
    })

    HorizontalPager(
        modifier = modifier,
        state = pagerState,
        pageSpacing = 10.dp,
        contentPadding = PaddingValues(horizontal = 15.dp)
    ) { page ->
        SummaryCard(
            modifier = Modifier.fillMaxSize(),
            store = storeList[page],
            onClick = { onClickStore(storeList[page]) }
        )
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { position ->
            onSelectStore.invoke(storeList[position])
        }
    }

}