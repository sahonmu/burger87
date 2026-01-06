package com.sahonmu.burger87.ui.theme.screens.map

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.Constants
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.viewmodels.StoreMapUiState

@Preview(showBackground = true)
@Composable
fun MapFloatingButtonBoxPreview() {
//    MapFloatingButtonBox(
//        modifier = Modifier
//    )
}

@Composable
fun MapFloatingButtonBox(
    modifier: Modifier = Modifier,
    storeMapUiState: StoreMapUiState,
    headerText: String,
    isIncludeCloseStore: Boolean,
    onMenu: () -> Unit = { },
    onStoreList: () -> Unit = { },
    onSearch: () -> Unit = { },
    onCluster: () -> Unit = { },
    onScore: (Float) -> Unit = { },
    onClear: () -> Unit = { },
    onIncludeCloseStore: () -> Unit = { }
) {

    val size = 44.dp


    Column (
        modifier = modifier,
    ) {
        HeightMargin(10.dp)
        MapHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            height = 44.dp,
            text = headerText,
            onMenu = { onMenu() },
            onSearch = { onSearch() },
            onClear = { onClear() }
        )
        
        if(headerText == Constants.HEADER_TEXT) {
            HeightMargin(10.dp)
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                val scoreList = storeMapUiState.originList.groupBy { it.score }.keys.sortedDescending()
                item { WidthMargin(10.dp) }
                items(scoreList) { score ->
                    BigScoreBox(
                        modifier = Modifier,
                        score = score,
                        onScore = { onScore(it) }
                    )
                }
                item { WidthMargin(10.dp) }
            }
        }

        HeightMargin(10.dp)
        Column(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            RoundButton(
                modifier = Modifier.size(size),
                painter = painterResource(id = R.drawable.ic_burger),
                imageSize = 17.dp,
                color = White,
                onClick = { onStoreList() }
            )

            if(storeMapUiState.storeList.isNotEmpty()) {
                RoundButton(
                    modifier = Modifier.size(size),
                    painter = painterResource(id = R.drawable.ic_cluster),
                    imageSize = 20.dp,
                    color = White,
                    onClick = { onCluster() }
                )
            }

            RoundButton(
                modifier = Modifier.size(size),
                textColor = if(isIncludeCloseStore) Base else Black,
                borderColor = if(isIncludeCloseStore) Base else White,
                text = "폐점\n포함",
                onClick = { onIncludeCloseStore() }
            )
        }
    }
}