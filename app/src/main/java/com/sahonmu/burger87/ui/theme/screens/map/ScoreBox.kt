package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Score
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.fontPadding

@Preview(showBackground = true)
@Composable
fun ScoreBoxPreview() {
    ScoreBox(score = 5.0f)
}

@Composable
fun ScoreBox(
    modifier: Modifier = Modifier,
    score: Float,
) {
    Box(
        modifier = modifier
            .height(20.dp)
            .background(color = White, shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = Score,
                shape = RoundedCornerShape(10.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.5.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(9.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Score)
            )
            Text(
                text = "$score",
                fontSize = 10.5.sp,
                color = Score,
                style = fontPadding,
                textAlign = TextAlign.Center
            )
        }
    }
}