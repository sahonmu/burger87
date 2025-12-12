package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton

@Preview(showBackground = true)
@Composable
fun MapFloatingButtonBoxPreview() {
    MapFloatingButtonBox(
        modifier = Modifier
    )
}

@Composable
fun MapFloatingButtonBox(
    modifier: Modifier = Modifier,
    onMenu: () -> Unit = { },
    onStoreList: () -> Unit = { },
    onSearch: () -> Unit = { },
) {

    val size = 44.dp

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        RoundButton(
            modifier = Modifier.size(size),
            painter = painterResource(id = R.drawable.ic_menu),
            imageSize = 18.dp,
            color = White,
            onClick = { onMenu() }
        )

        RoundButton(
            modifier = Modifier.size(size),
            painter = painterResource(id = R.drawable.ic_44_search),
            imageSize = 32.dp,
            color = White,
            onClick = { onSearch() }
        )

        RoundButton(
            modifier = Modifier.size(size),
            painter = painterResource(id = R.drawable.ic_burger),
            imageSize = 17.dp,
            color = White,
            onClick = { onStoreList() }
        )
    }

}