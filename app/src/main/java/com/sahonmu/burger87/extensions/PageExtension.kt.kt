package com.sahonmu.burger87.extensions

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
fun PagerState.moveItem(
    scope: CoroutineScope,
    page: Int,
    animate: Boolean = false
) {
    scope.launch {
        if (animate) {
            this@moveItem.animateScrollToPage(page)
        } else {
            this@moveItem.scrollToPage(page)
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun PagerState.nextItem(
    scope: CoroutineScope,
    animate: Boolean = false
) {
    val nextItem = this@nextItem.currentPage + 1
    if (nextItem < this@nextItem.pageCount) {
        scope.launch {
            if (animate) {
                this@nextItem.animateScrollToPage(nextItem)
            } else {
                this@nextItem.scrollToPage(nextItem)
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
fun PagerState.previousItem(
    scope: CoroutineScope,
    animate: Boolean = false
) {
    val previousItem = currentPage - 1
    if (previousItem >= 0) {
        scope.launch {
            if(animate) {
                this@previousItem.animateScrollToPage(previousItem)
            } else {
                this@previousItem.scrollToPage(previousItem)
            }
        }
    }
}

//@OptIn(ExperimentalFoundationApi::class)
//fun PagerState.moveItem(item: Int) {
//    mainScope().launch {
//        this@moveItem.scrollToPage(item)
//    }
//}


// ACTUAL OFFSET
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.offsetForPage(page: Int) = (currentPage - page) + currentPageOffsetFraction

// OFFSET ONLY FROM THE LEFT
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.startOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtLeast(0f)
}

// OFFSET ONLY FROM THE RIGHT
@OptIn(ExperimentalFoundationApi::class)
fun PagerState.endOffsetForPage(page: Int): Float {
    return offsetForPage(page).coerceAtMost(0f)
}