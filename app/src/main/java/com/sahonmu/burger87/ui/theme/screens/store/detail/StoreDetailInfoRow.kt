package com.sahonmu.burger87.ui.theme.screens.store.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Gray_700
import com.sahonmu.burger87.ui.theme.Base

@Composable
fun StoreDetailInfoRow(
    modifier: Modifier = Modifier,
    painter: Painter? = null,
    info: String,
    onClick: () -> Unit = { }
) {
    Row(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clickable(
                onClick = { onClick() }
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        painter?.let { res ->
            Image(
                modifier = Modifier.size(24.dp),
                painter = res,
                colorFilter = ColorFilter.tint(Base),
                contentDescription = null
            )
        }
        Text(
            text = info,
            fontSize = 16.sp,
            color = Gray_700
        )
    }

}


@Preview(showBackground = true)
@Composable
fun StoreDetailInfoRowPreview() {
    StoreDetailInfoRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        painter = painterResource(R.drawable.ic_instagram),
        info = "정보",
        onClick = { }
    )
}

