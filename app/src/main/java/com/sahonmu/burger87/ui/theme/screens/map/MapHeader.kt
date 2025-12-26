package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.Score
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.fontPadding
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin

@Preview(showBackground = true)
@Composable
fun MapHeaderPreview() {
    MapHeader(
        modifier = Modifier.fillMaxWidth(),
        height = 44.dp,
        text = "전체"
    )
}

@Composable
fun MapHeader(
    modifier: Modifier = Modifier,
    height: Dp,
    text: String,
    onMenu: () -> Unit = { },
    onSearch: () -> Unit = { },
    onClear: () -> Unit = { }
) {
    Row(
        modifier = modifier.height(height),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RoundButton(
            modifier = Modifier.size(height),
            painter = painterResource(id = R.drawable.ic_menu),
            imageSize = 18.dp,
            color = White,
            onClick = { onMenu() }
        )

        Box(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(height / 2),
                colors = CardDefaults.cardColors(
                    containerColor = White,
                ),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 5.dp
                ),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().clickable { onSearch() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WidthMargin(width = 10.dp)
                    Image(
                        modifier = Modifier.size(34.dp),
                        painter = painterResource(R.drawable.ic_44_search),
                        contentDescription = null
                    )
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        color = Black
                    )
                    if(text != Constants.HEADER_TEXT) {
                        Margin(modifier = Modifier.weight(1f))
                        Image(
                            modifier = Modifier.size(26.dp).clickable { onClear() },
                            painter = painterResource(R.drawable.ic_24_input_field_close),
                            contentDescription = null
                        )
                        WidthMargin(10.dp)
                    }
                }
            }
        }
    }
}
