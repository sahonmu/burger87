@file:OptIn(ExperimentalFoundationApi::class)

package com.sahonmu.burger87.ui.theme.screens.store.search

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.DataManager
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.screens.map.ScoreBox
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.vo.store.Store
import timber.log.Timber


@Preview(showBackground = true)
@Composable
fun StoreSearchRowPreview() {
    StoreSearchRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        keyword = "버거",
        store = DataManager.store()
    )
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun StoreSearchRow(
    modifier: Modifier = Modifier,
    store: Store,
    keyword: String,
    onClick: () -> Unit = { }
) {

    ConstraintLayout(
        modifier = modifier.clickable { onClick() }
    ) {
        val (image, name, score) = createRefs()

        Image(
            modifier = Modifier
                .size(21.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(name.start)
                    bottom.linkTo(parent.bottom)
                },
            painter = painterResource(R.drawable.ic_burger),
            contentDescription = null
        )

        Row(
            modifier = Modifier.constrainAs(name) {
                width = Dimension.fillToConstraints
                top.linkTo(parent.top)
                start.linkTo(image.end, margin = 10.dp)
                end.linkTo(score.start, margin = 10.dp)
                bottom.linkTo(parent.bottom)
            }
        ) {

            store.startIndex = store.fullName.indexOf(keyword)
            store.endIndex = store.startIndex + keyword.length
            if(store.isKeywordMatch()) {
                Text(
                    buildAnnotatedString {
                        append(store.fullName)
                        addStyle(
                            SpanStyle(color = Base),
                            store.startIndex,
                            store.endIndex
                        )
                    },
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    color = Gray_900
                )
            } else {
                Text(
                    text = store.fullName,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    color = Gray_900
                )
            }

        }

        ScoreBox(
            modifier = Modifier.constrainAs(score) {
                top.linkTo(parent.top)
                start.linkTo(name.end)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            score = store.score
        )
    }


}