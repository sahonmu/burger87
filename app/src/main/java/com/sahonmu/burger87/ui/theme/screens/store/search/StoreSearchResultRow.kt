@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.extensions.prettyCount
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin


@Preview(showBackground = true)
@Composable
fun StoreSearchResultRowPreview() {
    StoreSearchResultRow(
        modifier = Modifier.fillMaxWidth().height(56.dp),
        resultCount = 9155
    )
}

@Composable
fun StoreSearchResultRow(
    modifier: Modifier = Modifier,
    resultCount: Int,
    onCheck: (Boolean) -> Unit = { }
) {

    var checked by rememberSaveable { mutableStateOf(true) }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "검색 결과 ",
            fontSize = 14.sp,
            color = Gray_900
        )
        Text(
            resultCount.prettyCount(),
            fontSize = 14.sp,
            color = Base
        )
        Text(
            "개",
            fontSize = 14.sp,
            color = Gray_900
        )
        Margin(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "폐점 포함",
                    fontSize = 13.sp,
                    color = Base
                )
                Checkbox(
                    modifier = Modifier.size(30.dp),
                    colors = CheckboxDefaults.colors(
                        checkedColor = Base
                    ),
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                        onCheck(checked)
                    }
                )
            }
        }
    }
}