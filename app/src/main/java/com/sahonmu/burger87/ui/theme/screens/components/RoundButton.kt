package com.sahonmu.burger87.ui.theme.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.Gray_100
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.White


@Composable
@Preview
fun RoundButtonPreview() {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RoundButton(
            modifier = Modifier.size(56.dp),
            color = Color.LightGray,
            round = 28.dp,
            painter = painterResource(id = R.drawable.splash_logo)
        )
    }

}

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    color: Color = White,
    painter: Painter,
    round: Dp = 28.dp,
    imageSize: Dp = 28.dp,
//    colorFilter: Color = Black,
    borderColor: Color = Black,
    onClick: (() -> Unit)? = null
) {

    Card(
        modifier = modifier
            .clip(shape = RoundedCornerShape(round))
//            .shadow(
//                elevation = 12.dp,
//                shape = RoundedCornerShape(round),
//                clip = false
//            )
            .clickable {
                onClick?.invoke()
            },
        shape = RoundedCornerShape(round),
        colors = CardDefaults.cardColors(
            containerColor = color,
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(imageSize),
                painter = painter,
//                colorFilter = ColorFilter.tint(colorFilter),
                contentDescription = null
            )
        }
    }
}
