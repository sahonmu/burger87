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
import com.sahonmu.burger87.ui.theme.screens.info.data.ProvidingInfoScreen
import com.sahonmu.burger87.ui.theme.screens.info.event.SharingEventInfoScreen
import com.sahonmu.burger87.ui.theme.screens.info.main.InfoScreen
import com.sahonmu.burger87.ui.theme.screens.info.score.BasedOnScoreScreen
import com.sahonmu.burger87.ui.theme.screens.info.version.AppVersionScreen
import com.sahonmu.burger87.ui.theme.screens.map.MapScreen
import com.sahonmu.burger87.ui.theme.screens.splash.SplashScreen
import com.sahonmu.burger87.ui.theme.screens.store.detail.StoreDetailScreen
import com.sahonmu.burger87.ui.theme.screens.store.list.StoreListScreen
import com.sahonmu.burger87.ui.theme.screens.store.search.StoreSearchScreen
import com.sahonmu.burger87.viewmodels.AppInfoViewModel
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

fun NavGraphBuilder.info(
    navController: NavHostController
) {
    composable(Screens.INFO.route) { BaseScreen(content = { InfoScreen(navController = navController) })}
    composable(Screens.APP_VERSION.route) { BaseScreen(content = { AppVersionScreen(navController = navController) })}
    composable(Screens.BASED_ON_SCORE.route) { BaseScreen(content = { BasedOnScoreScreen(navController = navController) })}
    composable(Screens.PROVIDING_INFO.route) { BaseScreen(content = { ProvidingInfoScreen(navController = navController) })}
    composable(Screens.SHARING_EVENT_INFO.route) { BaseScreen(content = { SharingEventInfoScreen(navController = navController) })}
    composable(Screens.ANNOUNCEMENT_LIST.route) { BaseScreen(content = { AnnouncementListScreen(navController = navController) })}
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

@Composable
fun NavGraph(
    navController: NavHostController,
    screen: Screens
) {
    NavHost(navController = navController, startDestination = screen.route) {
        composable(Screens.SPLASH.route) {
            val appInfoViewModel: AppInfoViewModel = hiltViewModel()
            BaseScreen(
                content = {
                    SplashScreen(navController = navController, appInfoViewModel = appInfoViewModel)
                },
                viewModel = appInfoViewModel
            )

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
        store(navController= navController)
        info(navController = navController)
    }
}