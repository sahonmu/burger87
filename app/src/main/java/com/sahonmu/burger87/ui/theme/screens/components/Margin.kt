package com.sahonmu.burger87.ui.theme.screens.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Margin(
    modifier: Modifier = Modifier,
    width: Dp = 0.dp,
    height: Dp = 0.dp,
) {
    Spacer(
        modifier = modifier
            .width(width)
            .height(height)
    )
}

@Composable
fun Weight(modifier: Modifier) {
    Spacer(modifier = modifier)
}

@Composable
fun WidthMargin(
    width: Dp,
) {
    Spacer(
        modifier = Modifier.width(width)
    )
}

@Composable
fun HeightMargin(
    height: Dp,
) {
    Spacer(
        modifier = Modifier.height(height)
    )
}

@Composable
fun Line(
    height: Dp,
    color: Color,
    padding: Dp =  0.dp
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = padding)
            .height(height = height),
        color = color
    )
}