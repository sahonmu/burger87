package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin

@Preview(showBackground = true)
@Composable
fun MapAppBarPreview() {
    MapAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    )
}

@Composable
fun MapAppBar(
    modifier: Modifier = Modifier,
    onMenu: () -> Unit = { },
    onStoreList: () -> Unit = { },
    onScoreInfo: () -> Unit = { },
    onSearch: () -> Unit = { },
) {

    ConstraintLayout(
        modifier = modifier
    ) {
        val (left, right) = createRefs()

        Card(
            modifier = Modifier
                .fillMaxHeight()
                .constrainAs(left) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(right.start)
                    bottom.linkTo(parent.bottom)
                },
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
            elevation = CardDefaults.elevatedCardElevation(
                defaultElevation = 5.dp
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize().clickable { onSearch() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                WidthMargin(10.dp)
                RoundButton(
                    modifier = Modifier
                        .size(40.dp),
                    imageSize = 16.dp,
                    painter = painterResource(id = R.drawable.ic_menu),
                    onClick = { onMenu() }
                )
                WidthMargin(10.dp)
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    Text(
                        text = "상점 검색하기",
                        fontSize = 14.5.sp,
                        color = Gray_900
                    )
                }
            }
        }

        RoundButton(
            modifier = Modifier
                .size(56.dp)
                .constrainAs(right) {
                    top.linkTo(parent.top)
                    start.linkTo(left.end, margin = 5.dp)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            painter = painterResource(id = R.drawable.ic_burger),
            imageSize = 24.dp,
            color = White,
            onClick = {
                onStoreList()
            }
        )
    }

//    Card(
//        modifier = modifier,
//        shape = RoundedCornerShape(28.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Gray_50
//        ),
//        elevation = CardDefaults.elevatedCardElevation(
//            defaultElevation = 5.dp
//        )
//    ) {
//        Row(
//            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            WidthMargin(10.dp)
//            RoundButton(
//                modifier = Modifier
//                    .size(36.dp),
//                imageSize = 16.dp,
//                painter = painterResource(id = R.drawable.ic_menu),
//                onClick = { onMenu() }
//            )
//            Margin(modifier = Modifier.weight(1f))
//
//            RoundButton(
//                modifier = Modifier
//                    .size(36.dp),
//                painter = painterResource(id = R.drawable.ic_icon_store),
//                imageSize = 24.dp,
//                onClick = {
//                    onStoreList()
//
//                }
//            )
//            WidthMargin(10.dp)
//            RoundButton(
//                modifier = Modifier
//                    .size(36.dp),
//                imageSize = 24.dp,
//                painter = painterResource(id = R.drawable.ic_star),
//                borderColor = Color(0xFFFFD700),
//                onClick = { onScoreInfo() }
//            )
//            WidthMargin(10.dp)
//            RoundButton(
//                modifier = Modifier
//                    .size(36.dp),
//                imageSize = 24.dp,
//                painter = painterResource(id = R.drawable.ic_star),
//                borderColor = Color(0xFFFFD700),
//                onClick = { onSearch() }
//            )
//            WidthMargin(10.dp)
//        }
//    }
}