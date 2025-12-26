package com.sahonmu.burger87.ui.theme.screens.menu.event.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.RoundCap
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.utils.IntentUtils
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.vo.event.Event


@Preview(showBackground = true)
@Composable
fun EventDetailScreenScreenPreview() {
//    EventDetailScreen(rememberNavController())
}


@Composable
fun EventDetailScreen(
    navController: NavHostController,
    event: Event
) {

    val storeViewModel: StoreViewModel = hiltViewModel()
    val storeDetailUiState = storeViewModel.storeDetailUiState.collectAsState().value

//    event.storeId?.let { id ->
//        LaunchedEffect(Unit) {
//            storeViewModel.requestStoreDetailList(id)
//        }
//    }

    val uiState = rememberUiState()
    val context = uiState.context

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding()
        ) {

            Title(
                event = event,
                store = storeDetailUiState.store,
                onBack = { navController.popBackStack() },
                onStore = { navController.navigate("${Screens.STORE_DETAIL}/${storeDetailUiState.store!!.encode()}") },
                onLink = { }
            )


            Line(height = 1.dp, color = Gray_200)

            EventDetailBox(
                modifier = Modifier.fillMaxSize(),
                event = event,
                onLink = {
                    event.link?.let { url ->
                        IntentUtils.startActivityBrowser(context = context, url = url)
                    }
                }
            )
        }
    }
}

