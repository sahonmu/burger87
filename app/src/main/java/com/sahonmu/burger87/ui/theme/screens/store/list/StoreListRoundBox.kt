package com.sahonmu.burger87.ui.theme.screens.store.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_400
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.White


@Preview(showBackground = true)
@Composable
fun StoreListSortBoxPreview() {
    StoreListRoundBox(
        modifier = Modifier,
        text = "전체"
    )
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun StoreListRoundBox(
    modifier: Modifier = Modifier,
    isSelect: Boolean = false,
    text: String,
    onClick: () -> Unit = { }
) {

    Box(
        modifier = modifier
            .background(color = if (isSelect) Base else White, shape = RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.5.dp, horizontal = 8.dp),
            text = text,
            color = if (isSelect) White else Gray_600,
            fontSize = 12.sp
        )
    }

}
