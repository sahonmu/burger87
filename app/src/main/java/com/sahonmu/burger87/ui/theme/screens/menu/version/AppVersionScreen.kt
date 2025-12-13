package com.sahonmu.burger87.ui.theme.screens.menu.version

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.base.rememberUiState
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.Title
import com.sahonmu.burger87.utils.AppUtils.getAppVersionName
import com.sahonmu.burger87.utils.IntentUtils
import com.sahonmu.burger87.viewmodels.AppInfoViewModel

@Preview(showBackground = true)
@Composable
fun AppVersionScreenPreview() {
    AppVersionScreen(
        navController = rememberNavController(),
    )
}

@Composable
fun AppVersionScreen(
    navController: NavHostController
) {

    val context = rememberUiState().context
    val appInfoViewModel: AppInfoViewModel = hiltViewModel()

    val appInfoUiState =  appInfoViewModel.appInfoViewUiState.collectAsState().value

    LaunchedEffect(Unit) {
        appInfoViewModel.requestAppInfo()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .navigationBarsPadding()
    ) {

        val (header, body, footer) = createRefs()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(body.top)
                }
        ) {
            Title(
                title = "앱 버전",
                onBack = { navController.popBackStack() }
            )
            Line(height = 1.dp, color = Gray_200)
        }



        Box(
            modifier = Modifier.constrainAs(body) {
                height = Dimension.fillToConstraints
                top.linkTo(header.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(if (context.getAppVersionName() == appInfoUiState.appInfo.appVersion) parent.bottom else footer.top)
            },
            contentAlignment = Alignment.Center
        ) {

            Column() {
                Image(
                    contentDescription = null,
                    modifier = Modifier
                        .background(
                            color = Color.Transparent,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .size(100.dp),
                    painter = painterResource(R.drawable.splash_logo)
                )

                HeightMargin(height = 25.dp)

                context.getAppVersionName()?.let { versionName ->
                    Text(
                        text = "현재버전 : $versionName",
                        color = Base,
                        fontSize = 17.sp
                    )
                }

                HeightMargin(height = 10.dp)
                Text(
                    text = "최신버전 : ${appInfoUiState.appInfo.appVersion}",
                    color = Base,
                    fontSize = 17.sp
                )
            }

        }

        context.getAppVersionName()?.let {
            if (appInfoUiState.appInfo.appVersion != context.getAppVersionName() && appInfoUiState.appInfo.appVersionCode != 0) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .constrainAs(footer) {
                            top.linkTo(body.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }
                        .background(color = Base)
                        .clickable {
                            IntentUtils.startActivityForGooglePlay(context)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "업데이트 하러가기",
                        fontSize = 18.sp,
                        color = White
                    )
                }
            }
        }
    }
}