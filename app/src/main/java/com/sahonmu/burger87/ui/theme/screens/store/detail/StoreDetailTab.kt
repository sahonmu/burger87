package com.sahonmu.burger87.ui.theme.screens.store.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.StoreDetailTab
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.Dot_Un_Selected
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_700
import com.sahonmu.burger87.ui.theme.Gray_900

@Composable
fun StoreDetailTab(
    modifier: Modifier = Modifier,
    selectedTab: StoreDetailTab,
    onTab: (StoreDetailTab) -> Unit = { }
) {

    val isInfo = selectedTab == StoreDetailTab.INFO

    Box(
        modifier = modifier
    ) {
        Row(
//            modifier = modifier
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { onTab(StoreDetailTab.INFO) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "정보",
                    fontSize = 17.sp,
                    color = if (isInfo) Gray_700 else Dot_Un_Selected
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { onTab(StoreDetailTab.MENU) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "메뉴",
                    fontSize = 17.sp,
                    color = if (isInfo) Dot_Un_Selected else Gray_700
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StoreDetailTabPreview() {
    StoreDetailTab(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        selectedTab = StoreDetailTab.INFO
    )
}

