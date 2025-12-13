package com.sahonmu.burger87.extensions

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.enums.Transition


fun NavController.navigate(
    screens: Screens,
    query: String = ""
) {

    if (query.isNotEmpty()) {
        this.navigate("${screens.route}/$query") {
        }
    } else {
        this.navigate(
            route = screens.route,
        ) {
        }
    }
}

@Stable
fun Transition.enterTransition(
    duration: Int = 300
): EnterTransition {
    return when (this) {
        Transition.SLIDE_IN_HORIZONTAL -> {
            slideInHorizontally(
                initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(durationMillis = duration)
            )
        }

        Transition.SLIDE_IN_VERTICAL -> {
            slideInVertically(
                initialOffsetY = { fullHeight ->
                    fullHeight
                },
                animationSpec = tween(
                    durationMillis = duration,
                )
            )
        }

        Transition.SCALE_IN -> {
            scaleIn(
                initialScale = 0.0f,
                animationSpec = tween(
                    durationMillis = duration
                )
            )
        }

        else -> {
            EnterTransition.None
        }
    }
}

//@Stable
//fun Transition.exitTransition(
//    duration: Int = 300
//): ExitTransition {
//    return when (this) {
//        Transition.SLIDE_OUT_HORIZONTAL -> {
//            slideOutHorizontally(
//                targetOffsetX = { fullWidth -> -fullWidth },
//                animationSpec = tween(durationMillis = duration)
//            )
//        }
//
//        Transition.SLIDE_OUT_VERTICAL -> {
//            slideOutVertically(
//                targetOffsetY = { fullHeight ->
////                    fullHeight / 2
//                    fullHeight
//                },
//                animationSpec = tween(durationMillis = duration)
//            )
//        }
//
//        Transition.SCALE_OUT -> {
//            scaleOut(
//                targetScale = 0.5f,
//                animationSpec = tween(
//                    durationMillis = duration
//                )
//            )
//        }
//
//        else -> {
//            ExitTransition.None
//        }
//    }
//}

//fun NavController.navigate(
//    screens: Screens,
//    map: Map<String, String> = emptyMap()
//) {
//    if(map.isNotEmpty()) {
//        this.navigate(screens.route)
//    } else {
//    }
//}

//fun NavController.containRoute(route: String): Boolean {
//    return this.currentBackStack.value.any { it.destination.route == route }
//}
