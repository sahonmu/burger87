package com.sahonmu.burger87.ui.theme.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.ui.theme.Gray_600


@Composable
fun EmptyBox(
    emptyMessage: String
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = emptyMessage,
            fontSize = 17.sp,
            color = Gray_600
        )
    }

}