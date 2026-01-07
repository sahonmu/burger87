package com.sahonmu.burger87.ui.theme.sheet

import android.R
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahonmu.burger87.enums.MapApp
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.components.Line

@Composable
fun MapAppList(
    title: String,
    list: List<MapApp>,
    onClick: (MapApp) -> Unit = { }
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(44.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    modifier = Modifier,
                    text = title,
                    fontSize = 19.sp,
                    color = Gray_900
                )
            }
            Line(height = 1.dp, Gray_200)
        }

        LazyColumn {
            items(list) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .clickable { onClick(item) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = item.appName,
                        fontSize = 14.sp,
                        color = Gray_900
                    )
                }
            }
        }
    }
}