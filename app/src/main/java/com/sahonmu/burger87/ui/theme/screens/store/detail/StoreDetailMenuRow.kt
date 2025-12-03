package com.sahonmu.burger87.ui.theme.screens.store.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.extensions.prettyCount
import com.sahonmu.burger87.ui.theme.Gray_700
import com.sahonmu.burger87.ui.theme.Gray_900
import domain.sahonmu.burger87.vo.store.StoreMenu

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StoreDetailMenuRow(
    modifier: Modifier = Modifier,
    storeMenu: StoreMenu,
) {
    Row(
        modifier = modifier.padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier.size(90.dp),
            model = storeMenu.image,
            contentDescription = null
        ) {
            it.centerCrop()
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = storeMenu.name + "\n" + storeMenu.name,
                fontSize = 18.sp,
                color = Gray_900
            )

            Text(
                text = "₩${storeMenu.price.prettyCount()}",
                fontSize = 16.sp,
                color = Gray_700
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StoreDetailIMenuRowPreview() {
    StoreDetailMenuRow(
        storeMenu = StoreMenu(
            id = 0,
            storeId = 1,
            name = "치즈버거",
            price = 9999,
            image = "",
            description = ""
        )
    )
}

