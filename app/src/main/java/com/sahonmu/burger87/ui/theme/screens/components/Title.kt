package com.sahonmu.burger87.ui.theme.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sahonmu.burger87.R
import com.sahonmu.burger87.enums.EventState
import com.sahonmu.burger87.enums.Screens
import com.sahonmu.burger87.enums.checkSchedule
import com.sahonmu.burger87.extensions.encode
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_400
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.White
import domain.sahonmu.burger87.vo.announcement.Announcement
import domain.sahonmu.burger87.vo.event.Event
import domain.sahonmu.burger87.vo.store.Store

@Preview(showBackground = true)
@Composable
fun TitlePreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        SearchTitle(
            modifier = Modifier.fillMaxWidth(),
        )
    }

}


@Composable
fun Title(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit = { }
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
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
                .clip(RoundedCornerShape(28.dp))
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
                text = title,
                fontSize = 16.sp,
                color = Gray_900
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

        }
    }
}

@Composable
fun TitleWithIncludeClosed(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit = { },
    onCheck: (Boolean) -> Unit = { }
) {

    var checked by rememberSaveable { mutableStateOf(true) }

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
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
                .clip(RoundedCornerShape(28.dp))
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
                text = title,
                fontSize = 16.sp,
                color = Gray_900
            )

        }

        Row(
            modifier = Modifier
                .constrainAs(
                    empty
                ) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(text.end)
                    end.linkTo(parent.end)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
//            Text(
//                text = "폐점 포함",
//                fontSize = 13.sp,
//                color = Base
//            )
//            Checkbox(
//                modifier = Modifier.size(30.dp),
//                colors = CheckboxDefaults.colors(
//                    checkedColor = Base
//                ),
//                checked = checked,
//                onCheckedChange = {
//                    checked = it
//                    onCheck(checked)
//                }
//            )
//            WidthMargin(10.dp)
        }
    }

}

@Composable
fun SearchTitle(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = { },
    onKeyword: (String) -> Unit = { },
) {

    var keyword by rememberSaveable { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(28.dp))
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

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Gray_50
            )
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 20.dp, top = 6.dp, bottom = 6.dp)
                    .border(width = 1.dp, color = Gray_200, shape = RoundedCornerShape(24.dp))
                    .background(color = White, shape = RoundedCornerShape(24.dp))
            ) {
                val (left, right) = createRefs()

                BasicTextField(
                    modifier = Modifier
                        .focusRequester(focusRequester = focusRequester)
                        .fillMaxWidth()
                        .padding(vertical = 1.5.dp, horizontal = 13.dp)
                        .constrainAs(left) {
                            width = Dimension.fillToConstraints
                            top.linkTo(anchor = parent.top)
                            start.linkTo(anchor = parent.start)
                            end.linkTo(anchor = right.start)
                            bottom.linkTo(anchor = parent.bottom)
                        },
                    value = keyword,
                    onValueChange = {
                        keyword = it
                        onKeyword(keyword)
                    },
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        Box(
                            modifier = Modifier
                                .height(56.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (keyword.isEmpty()) {
                                Text(
                                    text = "키워드를 입력하세요.",
                                    color = Gray_400,
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )


                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .constrainAs(right) {
                            top.linkTo(anchor = parent.top)
                            start.linkTo(anchor = left.end)
                            end.linkTo(anchor = parent.end, margin = 10.dp)
                            bottom.linkTo(anchor = parent.bottom)
                        }) {

                    if (keyword.isNotEmpty()) {
                        Image(
                            modifier = Modifier
                                .clip(RoundedCornerShape(15.dp))
                                .fillMaxSize()
                                .clickable {
                                    keyword = ""
                                    onKeyword(keyword)
                                },
                            painter = painterResource(R.drawable.ic_24_input_field_close),
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun AnnouncementDetailTitle(
    modifier: Modifier = Modifier,
    title: String,
    announcement: Announcement,
    onBack: () -> Unit = { },
) {

    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
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
                .clip(RoundedCornerShape(28.dp))
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
                text = title,
                fontSize = 16.sp,
                color = Gray_900
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
            if(announcement.storeId != null) {
                RoundButton(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(id = R.drawable.ic_burger),
                    imageSize = 17.dp,
                    color = White,
                    onClick = {  }
                )
            }
        }
    }
}

@Composable
fun Title(
    event: Event,
    store: Store? = null,
    link: String? = null,
    onBack: () -> Unit = { },
    onStore: () -> Unit = { },
    onLink: () -> Unit = { }
) {

    val eventState = checkSchedule(
        startDate = event.startDate,
        endDate = event.endDate
    )
    val color = when (eventState) {
        EventState.SCHEDULED -> {
            Base
        }
        EventState.FINISHED -> {
            Gray_400
        }
        else -> {
            Gray_900
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .clickable { onBack() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_44_back),
                contentDescription = null
            )
        }
        Text(
            text = eventState.state,
            fontSize = 16.sp,
            color = Gray_900
        )
        Margin(modifier = Modifier.weight(1f))

//        store?.let {
//            RoundButton(
//                modifier = Modifier.size(36.dp),
//                painter = painterResource(id = R.drawable.ic_burger),
//                imageSize = 17.dp,
//                color = White,
//                onClick = { onStore() }
//            )
//            WidthMargin(width = 16.dp)
//        }
//
//        link?.let {
//            RoundButton(
//                modifier = Modifier.size(36.dp),
//                painter = painterResource(id = R.drawable.ic_call),
//                imageSize = 17.dp,
//                color = White,
//                onClick = { onLink() }
//            )
//            WidthMargin(width = 16.dp)
//        }
    }
}


