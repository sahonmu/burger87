package com.sahonmu.burger87.ui.theme.screens.menu.main

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
//import com.mikepenz.aboutlibraries.ui.compose.android.produceLibraries
//import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.sahonmu.burger87.enums.InfoMenu
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.utils.IntentUtils

@Preview(showBackground = true)
@Composable
fun MenuListScreenPreview() {
    MenuListScreen(
        navController = rememberNavController(),
    )
}

@Composable
fun MenuListScreen(
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
            title = "메뉴",
            onBack = { navController.popBackStack() }
        )

        Line(height = 1.dp, color = Gray_200)

//        val libraries by produceLibraries()
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(White)
//        ) {
//            LibrariesContainer(
//                libraries = libraries,
//                modifier = Modifier.fillMaxSize()
//            )
//        }

//        LibrariesContainer(
//            libs = libs,
//            onClick = { library ->
//                LibraryDetails(
//                    library = library,
//                    onBack = { /* 뒤로가기 */ }
//                )
//            },
//            onBack = {  }
//        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            itemsIndexed(menuList) { index, item ->
                MenuRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    menu = menuList[index].menuName,
                    onClick = {
                        if (item == InfoMenu.INSTAGRAM) {
                            IntentUtils.startActivityForInstagram(
                                context,
                                "https://www.instagram.com/_burger87_"
                            )
                        } else if (item == InfoMenu.BASED_ON_SCORE) {
                            navController.navigate(Screens.BASED_ON_SCORE.route)
                        } else if (item == InfoMenu.PROVIDING_INFO) {
                            navController.navigate(Screens.PROVIDING_INFO.route)
                        } else if (item == InfoMenu.SHARING_EVENT_INFO) {
                            navController.navigate(Screens.EVENT_LIST.route)
                        } else if (item == InfoMenu.ANNOUNCEMENT) {
                            navController.navigate(Screens.ANNOUNCEMENT_LIST.route)
                        } else if (item == InfoMenu.REPORT) {
                            IntentUtils.startActivityForBurgerReport(context)
//                        } else if (item == InfoMenu.OPEN_SOURCE) {
//                            OssLicensesMenuActivity.setActivityTitle("오픈소스 라이선스")
//                            context.startActivity(Intent(context, OssLicensesActivity::class.java))
                        } else if (item == InfoMenu.VERSION_INFO) {
                            navController.navigate(Screens.APP_VERSION.route)
                        } else if (item == InfoMenu.STATISTICS) {
                            navController.navigate(Screens.STATISTICS.route)
                        }
                    }
                )

                if (index != menuList.lastIndex) {
                    Line(height = 1.dp, color = Gray_200)
                }
            }
        }
    }
}