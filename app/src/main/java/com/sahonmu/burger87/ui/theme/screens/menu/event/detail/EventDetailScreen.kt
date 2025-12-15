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
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Margin
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
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

    event.storeId?.let { id ->
        LaunchedEffect(Unit) {
            storeViewModel.requestStoreDetailList(id)
        }
    }

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .navigationBarsPadding()
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .clickable { navController.popBackStack() },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_44_back),
                        contentDescription = null
                    )
                }
                Margin(modifier = Modifier.weight(1f))

                storeDetailUiState.store?.let { store ->
                    RoundButton(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.ic_burger),
                        imageSize = 17.dp,
                        color = White,
                        onClick = { navController.navigate("${Screens.STORE_DETAIL}/${store.encode()}") }
                    )
                    WidthMargin(width = 16.dp)
                }

                event.link?.let { link ->
                    RoundButton(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.ic_call),
                        imageSize = 17.dp,
                        color = White,
                        onClick = { link }
                    )
                    WidthMargin(width = 16.dp)
                }
            }

            Line(height = 1.dp, color = Gray_200)

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

            }
        }
    }
}

