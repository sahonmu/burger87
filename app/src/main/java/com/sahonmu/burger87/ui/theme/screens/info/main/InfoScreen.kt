package com.sahonmu.burger87.ui.theme.screens.info.main

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
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title

@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreen(
        navController = rememberNavController(),
    )
}

@Composable
fun InfoScreen(
    navController: NavHostController
) {

    Column(
        modifier = Modifier.fillMaxSize().systemBarsPadding().navigationBarsPadding()
    ) {
        Title(
            title = "설정",
            onBack = { navController.popBackStack() }
        )

        Line(height = 1.dp, color = Gray_200)
    }
}