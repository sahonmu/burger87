package com.sahonmu.burger87.ui.theme.screens.info.data

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.enums.InfoMenu
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title


@Preview(showBackground = true)
@Composable
fun ProvidingInfoScreenScreenPreview() {
    ProvidingInfoScreen(rememberNavController())
}


@Composable
fun ProvidingInfoScreen(
    navController: NavHostController,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {
        Title(
            title = InfoMenu.PROVIDING_INFO.menuName,
            onBack = { navController.popBackStack() }
        )
        Line(height = 1.dp, color = Gray_200)
    }
}

