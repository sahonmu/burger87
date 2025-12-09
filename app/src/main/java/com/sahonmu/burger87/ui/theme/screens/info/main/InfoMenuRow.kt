package com.sahonmu.burger87.ui.theme.screens.info.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin


@Preview(showBackground = true)
@Composable
fun InfoMenuRowPreview() {
    InfoMenuRow(
        painter = painterResource(id = R.drawable.ic_instagram),
        menu = "버거87 인스타그램"
    )
}

@Composable
fun InfoMenuRow(
    modifier: Modifier = Modifier,
    painter: Painter,
    menu: String,
    onClick: () -> Unit = { }
) {

    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        WidthMargin(width = 10.dp)
        Image(
            modifier = Modifier.size(22.dp),
            painter = painter,
            contentDescription = null
        )
        WidthMargin(8.dp)
        Text(
            text = menu,
            fontSize = 15.sp,
            color = Gray_600
        )
        Margin(modifier = Modifier.weight(1f))
        WidthMargin(width = 20.dp)

    }

}