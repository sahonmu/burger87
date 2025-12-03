package com.sahonmu.burger87.ui.theme.screens.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sahonmu.burger87.ui.theme.Dot_Un_Selected
import com.sahonmu.burger87.ui.theme.SplashBackground


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    dotSize: Dp = 6.dp,
    padding: Dp = 2.dp,
) {
    Row(
        modifier.height(dotSize),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) SplashBackground else Dot_Un_Selected
            Box(
                modifier = Modifier
                    .padding(horizontal = padding)
                    .background(color, CircleShape)
                    .size(dotSize)
            )
        }
    }
}