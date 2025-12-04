package com.sahonmu.burger87.ui.theme.screens.store.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.extensions.prettyCount
import com.sahonmu.burger87.ui.theme.Gray_700
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.White
import domain.sahonmu.burger87.vo.store.StoreMenu

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun StoreDetailMenuRow(
    modifier: Modifier = Modifier,
    storeMenu: StoreMenu,
    onClick: (String) -> Unit = { }
) {
    Row(
        modifier = modifier.padding(horizontal = 4.dp).clickable { onClick(storeMenu.description) },
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .size(80.dp).padding(vertical = 4.dp)
                .clip(RoundedCornerShape(8.dp)),
            model = storeMenu.image,
            contentDescription = null,
        ) {
            it.centerCrop()
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = storeMenu.name,
                fontSize = 17.sp,
                color = Gray_900
            )

            Text(
                text = "₩${storeMenu.price.prettyCount()}",
                fontSize = 15.sp,
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

