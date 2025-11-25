package com.sahonmu.burger87.ui.theme.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.ui.theme.base.BaseScreen
import com.sahonmu.burger87.ui.theme.screens.splash.SplashScreen


fun NavGraphBuilder.map(
    navController: NavHostController,
) {
//    composable(Screens.SPLASH.route) {
//        BaseScreen(
//            content = {
//                SplashScreen(
//                    navController = navController,
////                    authViewModel = authViewModel
//                )
//            },
//            viewDescription = "View_Onboarding"
//        )
//    }
}


//fun NavGraphBuilder.community(
//    navController: NavHostController
//) {
//
//    composable(
//        route = "${Screens.COMMUNITY_POST_DETAIL.route}/{${BundleKey.ID}}",
//        deepLinks = listOf(
//            navDeepLink {
//                uriPattern = "andreia://campus?path={path}&id={id}"
//            }
//        )
//    ) {
//        it.arguments?.getString(BundleKey.ID)?.let { id ->
//            val communityViewModel: CommunityViewModel = hiltViewModel()
//            BaseScreen(
//                content = {
//                    CommunityPostDetailScreen(
//                        navController = navController,
//                        id = id,
//                        communityViewModel = communityViewModel,
//                    )
//                },
//                viewModel = communityViewModel,
//            )
//        }
//    }
//    composable(
//        route = "${Screens.COMMUNITY_POST_DETAIL.route}/{${BundleKey.ID}}/{${BundleKey.COMMENT_ID}}",
//        deepLinks = listOf(
//            navDeepLink {
//                uriPattern = "andreia://campus?path={path}&id={id}&subId={subId}"
//            }
//        )
//    ) {
//        val id = it.arguments?.getString(BundleKey.ID)
//        val commentId = it.arguments?.getString(BundleKey.COMMENT_ID)
//        if (id != null && commentId != null) {
//            val communityViewModel: CommunityViewModel = hiltViewModel()
//            BaseScreen(
//                content = {
//                    CommunityPostDetailScreen(
//                        navController = navController,
//                        id = id,
//                        scrollCommentId = commentId.toInt(),
//                        communityViewModel = communityViewModel,
//                    )
//                },
//                viewModel = communityViewModel,
//            )
//        }
//    }
//}

@Composable
fun NavGraph(
    navController: NavHostController,
    screen: Screens
) {
    NavHost(navController = navController, startDestination = screen.route) {
        // splash
        composable(Screens.SPLASH.route) {
            BaseScreen(content = { SplashScreen(navController = navController) })
        }

//        map(navController)
    }
}