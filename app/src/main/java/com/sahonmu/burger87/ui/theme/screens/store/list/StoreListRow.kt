package com.sahonmu.burger87.ui.theme.screens.store.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.R
import com.sahonmu.burger87.common.DataManager
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Score
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.fontPadding
import com.sahonmu.burger87.ui.theme.screens.components.Alert
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.WidthMargin
import com.sahonmu.burger87.utils.math.MathUtils
import domain.sahonmu.burger87.enums.StoreState
import domain.sahonmu.burger87.enums.isOperation
import domain.sahonmu.burger87.enums.storeState
import domain.sahonmu.burger87.vo.store.Store


@Preview(showBackground = true)
@Composable
fun StoreListRowPreview() {
    StoreListRow(
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        store = DataManager.store()
    )
}

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun StoreListRow(
    modifier: Modifier = Modifier,
    store: Store,
    onClick: (Store) -> Unit = { }
) {

    var showAlert by rememberSaveable { mutableStateOf(false) }

    if (showAlert) {
        Alert(
            message = "폐업된 점포입니다.",
            onDismissRequest = { showAlert = false }
        )
    }

    Card(
        modifier = modifier.height(110.dp),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(
            containerColor = White,
        ),
        onClick = {
            if(store.storeState.isOperation()) {
                onClick(store)
            } else {
                showAlert = true
            }
        }
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {

            val (left, center, right) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(left) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(center.start)
                        bottom.linkTo(parent.bottom)
                    }
                    .size(110.dp),
            ) {
                GlideImage(
                    modifier = Modifier.fillMaxSize(),
                    model = store.thumbImage,
                    contentDescription = null
                ) {
                    it.centerCrop()
                }
            }
            Column(
                modifier = Modifier.constrainAs(center) {
                    width = Dimension.fillToConstraints
                    top.linkTo(parent.top)
                    start.linkTo(left.end, margin = 10.dp)
                    end.linkTo(right.start, margin = 8.dp)
                    bottom.linkTo(parent.bottom)
                }
            ) {

                Text(
                    text = if (store.branch.isEmpty()) store.name else "${store.name}(${store.branch})",
                    fontSize = 14.5.sp,
                    style = fontPadding
                )

                if (store.storeState.isOperation()) {
                    HeightMargin(height = 4.dp)
                    Text(
                        text = store.address,
                        fontSize = 11.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = fontPadding
                    )
                }

                if(store.distance != 0.0) {
                    HeightMargin(height = 4.dp)
                    Text(
                        text = MathUtils.formatMeterToKm(store.distance),
                        fontSize = 10.sp,
                        maxLines = 1,
                        color = Base,
                        overflow = TextOverflow.Ellipsis,
                        style = fontPadding
                    )
                }
            }

            Column(
                modifier = Modifier.constrainAs(right) {
                    top.linkTo(parent.top)
                    start.linkTo(center.end)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom)
                },
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .background(color = White, shape = RoundedCornerShape(10.dp))
                        .border(
                            width = 1.dp,
                            color = Score,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.5.dp),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(9.dp),
                            painter = painterResource(R.drawable.ic_star),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Score)
                        )
                        Text(
                            text = "${store.score}",
                            fontSize = 10.5.sp,
                            color = Score,
                            style = fontPadding,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                if (store.state.storeState() == StoreState.CLOSED) {
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .background(color = White, shape = RoundedCornerShape(10.dp))
                            .border(
                                width = 1.dp,
                                color = Base,
                                shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.5.dp),
                            text = "폐점",
                            fontSize = 10.5.sp,
                            color = Base,
                            style = fontPadding,
                            textAlign = TextAlign.Center
                        )
                    }
                    WidthMargin(8.dp)
                }
            }

            if (!store.storeState.isOperation()) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x77E7E7ED)))
            }
        }
    }
}


