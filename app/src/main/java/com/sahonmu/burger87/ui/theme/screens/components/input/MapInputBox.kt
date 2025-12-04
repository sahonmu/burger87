package com.sahonmu.burger87.ui.theme.screens.components.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sahonmu.burger87.ui.theme.White


@Composable
@Preview
fun MapInputBoxPreview(
    modifier: Modifier = Modifier,
    color: Color = White,
) {
    Column {
        MapInputBox(
            modifier = Modifier.fillMaxWidth().height(56.dp)
        )
    }
}

@Composable
fun MapInputBox(
    modifier: Modifier = Modifier,
    color: Color = White,
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
//            containerColor = color,
            contentColor = color
        ),
    ) {
        Row (
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 20.dp),
                text = "입력하세요.",
                textAlign = TextAlign.Center
            )
        }
    }
}
