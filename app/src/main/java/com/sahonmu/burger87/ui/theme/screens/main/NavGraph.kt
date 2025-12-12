package com.sahonmu.burger87.ui.theme.screens.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sahonmu.burger87.common.BundleKey
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.extensions.decode
import com.sahonmu.burger87.extensions.decodeAnnouncement
import com.sahonmu.burger87.extensions.decodeList
import com.sahonmu.burger87.ui.theme.base.BaseScreen
import com.sahonmu.burger87.ui.theme.screens.announcement.detail.AnnouncementDetailScreen
import com.sahonmu.burger87.ui.theme.screens.announcement.list.AnnouncementListScreen
import com.sahonmu.burger87.ui.theme.screens.info.main.InfoScreen
import com.sahonmu.burger87.ui.theme.screens.info.version.AppVersionScreen
import com.sahonmu.burger87.ui.theme.screens.map.MapScreen
import com.sahonmu.burger87.ui.theme.screens.score.ScoreCriteriaScreen
import com.sahonmu.burger87.ui.theme.screens.splash.SplashScreen
import com.sahonmu.burger87.ui.theme.screens.store.detail.StoreDetailScreen
import com.sahonmu.burger87.ui.theme.screens.store.list.StoreListScreen
import com.sahonmu.burger87.ui.theme.screens.store.search.StoreSearchScreen
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.vo.announcement.Announcement
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber


//fun NavGraphBuilder.map(
//    navController: NavHostController,
//) {
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
//}


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

fun NavGraphBuilder.announcement(
    navController: NavHostController
) {

    composable(Screens.ANNOUNCEMENT_LIST.route) {
        BaseScreen(content = { AnnouncementListScreen(navController = navController) })
    }

    composable("${Screens.ANNOUNCEMENT_DETAIL.route}/{${BundleKey.DATA}}") {
        it.arguments?.getString(BundleKey.DATA)?.let { json ->
            json.decodeAnnouncement().let { announcement ->
                BaseScreen(
                    content = {
                        AnnouncementDetailScreen(
                            navController = navController,
                            announcement = announcement as Announcement
                        )
                    },
                )
            }
        }
    }
}

fun NavGraphBuilder.store(
    navController: NavHostController
) {
    composable("${Screens.STORE_DETAIL.route}/{${BundleKey.DATA}}") {
        it.arguments?.getString(BundleKey.DATA)?.let { json ->
            json.decode().let { store ->
                BaseScreen(
                    content = {
                        StoreDetailScreen(
                            navController = navController,
                            store = store as Store
                        )
                    },

                )
            }
        }
    }

    composable("${Screens.STORE_LIST.route}/{${BundleKey.DATA}}") {
        it.arguments?.getString(BundleKey.DATA)?.let { json ->
            Timber.i("list = ${json.decodeList().toMutableList()}")
                BaseScreen(
                    content = {
                        StoreListScreen(
                            navController = navController,
                            storeList = json.decodeList().toMutableList()
                        )
                    },
                )
        }
    }


    composable("${Screens.STORE_SEARCH.route}/{${BundleKey.DATA}}") {
        it.arguments?.getString(BundleKey.DATA)?.let { json ->
            Timber.i("list = ${json.decodeList().toMutableList()}")
            BaseScreen(
                content = {
                    StoreSearchScreen(
                        navController = navController,
                        storeList = json.decodeList().toMutableList()
                    )
                },
            )
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    screen: Screens
) {
    NavHost(navController = navController, startDestination = screen.route) {
        // 스플래쉬
        composable(Screens.SPLASH.route) {
            BaseScreen(content = { SplashScreen(navController = navController) })
        }

        // 지도
        composable(Screens.MAP.route) {
            val storeViewModel: StoreViewModel = hiltViewModel()
            BaseScreen(
                content = {
                    MapScreen(
                        navController = navController,
                        storeViewModel = storeViewModel
                    )
                }
            )
        }

        store(navController)

        composable(Screens.INFO.route) {
            BaseScreen(content = { InfoScreen(navController = navController) })
        }

        announcement(navController)

        composable(Screens.APP_VERSION.route) {
            BaseScreen(content = { AppVersionScreen(navController = navController) })
        }
        composable(Screens.SCORE_CRITERIA.route) {
            BaseScreen(content = { ScoreCriteriaScreen(navController = navController) })
        }
    }
}