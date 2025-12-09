package com.sahonmu.burger87.ui.theme.screens.announcement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.InfoMenu
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.info.main.InfoMenuRow
import com.sahonmu.burger87.utils.log.IntentUtils

@Preview(showBackground = true)
@Composable
fun AnnouncementListScreenPreview() {
    AnnouncementListScreen(
        navController = rememberNavController(),
    )
}

@Composable
fun AnnouncementListScreen(
    navController: NavHostController
) {

    val context = rememberUiState().context
    val menuList = InfoMenu.entries.toMutableList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            title = "공지사항",
            onBack = { navController.popBackStack() }
        )

        Line(height = 1.dp, color = Gray_200)

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            itemsIndexed(menuList) { index, item ->
                InfoMenuRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    painter = painterResource(id = menuList[index].menuResource),
                    menu = menuList[index].menuName,
                    onClick = {
                        if (item == InfoMenu.INSTAGRAM) {
                            IntentUtils.startActivityForInstagram(
                                context,
                                "https://www.instagram.com/_burger87_"
                            )
                        } else if (item == InfoMenu.ANNOUNCEMENT) {
                        } else if (item == InfoMenu.REPORT) {
                        } else if (item == InfoMenu.OPEN_SOURCE) {
                        } else if (item == InfoMenu.VERSION_INFO) {
                        }
                    }
                )
            }


            item {
                InfoMenuRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    painter = painterResource(id = R.drawable.ic_instagram),
                    menu = "버거87 인스타그램",
                    onClick = {
                        IntentUtils.startActivityForInstagram(
                            context,
                            "https://www.instagram.com/_burger87_"
                        )
                    }
                )
                Line(height = 1.dp, color = Gray_200)
            }
        }
    }
}