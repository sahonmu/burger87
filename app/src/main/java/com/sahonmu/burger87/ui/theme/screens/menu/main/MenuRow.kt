package com.sahonmu.burger87.ui.theme.screens.menu.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.ui.theme.Gray_900


@Preview(showBackground = true)
@Composable
fun MenuRowPreview() {
    MenuRow(
        menu = "버거87 인스타그램"
    )
}

@Composable
fun MenuRow(
    modifier: Modifier = Modifier,
    menu: String,
    onClick: () -> Unit = { }
) {

    Row(
        modifier = modifier.clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 20.dp),
            text = menu,
            fontSize = 15.sp,
            color = Gray_900,
        )
    }
}