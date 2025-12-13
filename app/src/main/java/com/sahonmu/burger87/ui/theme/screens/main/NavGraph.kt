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
import com.sahonmu.burger87.extensions.decodeEvent
import com.sahonmu.burger87.extensions.decodeList
import com.sahonmu.burger87.ui.theme.base.BaseScreen
import com.sahonmu.burger87.ui.theme.screens.announcement.detail.AnnouncementDetailScreen
import com.sahonmu.burger87.ui.theme.screens.announcement.list.AnnouncementListScreen
import com.sahonmu.burger87.ui.theme.screens.menu.data.ProvidingInfoScreen
import com.sahonmu.burger87.ui.theme.screens.menu.event.detail.EventDetailScreen
import com.sahonmu.burger87.ui.theme.screens.menu.event.list.EventListScreen
import com.sahonmu.burger87.ui.theme.screens.menu.main.MenuListScreen
import com.sahonmu.burger87.ui.theme.screens.menu.score.BasedOnScoreScreen
import com.sahonmu.burger87.ui.theme.screens.menu.version.AppVersionScreen
import com.sahonmu.burger87.ui.theme.screens.map.MapScreen
import com.sahonmu.burger87.ui.theme.screens.splash.SplashScreen
import com.sahonmu.burger87.ui.theme.screens.store.detail.StoreDetailScreen
import com.sahonmu.burger87.ui.theme.screens.store.list.StoreListScreen
import com.sahonmu.burger87.ui.theme.screens.store.search.StoreSearchScreen
import com.sahonmu.burger87.viewmodels.AppInfoViewModel
import com.sahonmu.burger87.viewmodels.StoreViewModel
import domain.sahonmu.burger87.vo.announcement.Announcement
import domain.sahonmu.burger87.vo.event.Event
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber


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
    composable(Screens.MENU.route) { BaseScreen(content = { MenuListScreen(navController = navController) })}
    composable(Screens.APP_VERSION.route) { BaseScreen(content = { AppVersionScreen(navController = navController) })}
    composable(Screens.BASED_ON_SCORE.route) { BaseScreen(content = { BasedOnScoreScreen(navController = navController) })}
    composable(Screens.PROVIDING_INFO.route) { BaseScreen(content = { ProvidingInfoScreen(navController = navController) })}
    composable(Screens.EVENT_LIST.route) { BaseScreen(content = { EventListScreen(navController = navController) })}
    composable(Screens.EVENT_DETAIL.route) { BaseScreen(content = { EventListScreen(navController = navController) })}
    composable("${Screens.EVENT_DETAIL.route}/{${BundleKey.DATA}}") {
        it.arguments?.getString(BundleKey.DATA)?.let { json ->
            json.decodeEvent().let { event ->
                BaseScreen(
                    content = {
                        EventDetailScreen(
                            navController = navController,
                            event = event as Event
                        )
                    },
                )
            }
        }
    }
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
                },
                viewModel = storeViewModel
            )
        }
        store(navController= navController)
        info(navController = navController)
    }
}