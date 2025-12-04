package com.sahonmu.burger87.ui.theme.screens.store.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sahonmu.burger87.R
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.Score
import domain.sahonmu.burger87.enums.StoreState

@Composable
fun StoreDetailTitle(
    modifier: Modifier = Modifier,
    title: String,
    branch: String = "",
    score: Float,
    state: StoreState = StoreState.OPERATION,
    onBack: () -> Unit = { }
) {

    ConstraintLayout(
        modifier = modifier.fillMaxSize(),
    ) {

        val (back, text, empty) = createRefs()

        Box(
            modifier = Modifier
                .size(56.dp)
                .constrainAs(
                    back
                ) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(text.start)
                }
                .clickable(
                    onClick = { onBack() }
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_44_back),
                contentDescription = null
            )
        }

        Row(
            modifier = Modifier.constrainAs(
                text
            ) {
                width = Dimension.fillToConstraints
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(back.end)
                end.linkTo(empty.start)
            },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = if (branch.isEmpty()) title else "${title}(${branch})",
                fontSize = 16.sp,
                color = Gray_900
            )
            Text(
                text = "(★${score})",
                fontSize = 14.sp,
                color = Score
            )
        }

        Box(
            modifier = Modifier
                .constrainAs(
                    empty
                ) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(text.end)
                    end.linkTo(parent.end)
                }
                .size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            if (state == StoreState.CLOSED) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Image(
//                        modifier = Modifier.size(18.dp),
//                        painter = painterResource(R.drawable.emoji_sad_selected),
//                        contentDescription = null
//                    )
                    Text(
                        color = Base,
                        text = "폐점",
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun StoreDetailTitlePreview() {
//    StoreDetailScreen(rememberNavController())
}

