package com.sahonmu.burger87.ui.theme.screens.menu.event.detail

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.sahonmu.burger87.R
import com.sahonmu.burger87.extensions.toYearMonthDay
import com.sahonmu.burger87.ui.theme.Gray_200
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Gray_900
import com.sahonmu.burger87.ui.theme.White
import com.sahonmu.burger87.ui.theme.fontPadding
import com.sahonmu.burger87.ui.theme.screens.components.HeightMargin
import com.sahonmu.burger87.ui.theme.screens.components.Line
import com.sahonmu.burger87.ui.theme.screens.components.RoundButton
import domain.sahonmu.burger87.vo.event.Event
import timber.log.Timber


@Preview(showBackground = true)
@Composable
fun EventDetailBoxPreview() {
//    EventDetailScreen(rememberNavController())
}


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun EventDetailBox(
    modifier: Modifier = Modifier,
    event: Event,
    onLink: () -> Unit = { }
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = event.title,
                    color = Gray_900,
                    fontSize = 15.sp
                )
                HeightMargin(height = 3.dp)
                val startDate = event.startDate.toYearMonthDay()
                val endDate = event.endDate.toYearMonthDay()
                Text(
                    text = if (startDate == endDate) startDate else "${startDate}~${endDate}",
                    color = Gray_600,
                    fontSize = 13.sp
                )

                HeightMargin(10.dp)
                Line(height = 1.dp, color = Gray_200)
                HeightMargin(10.dp)
                Text(
                    text = event.contents,
                    color = Gray_900,
                    fontSize = 14.sp
                )
                HeightMargin(10.dp)
            }

            event.image?.let { url ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    GlideImage(
                        modifier = Modifier.fillMaxSize(),
                        model = url,
                        contentDescription = null
                    ) {
                        it.centerInside()
                    }
                }

//                        Line(height = 1.dp, color = Gray_200)
            }

        }

        event.link?.let {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 20.dp, bottom = 20.dp)
            ) {
                RoundButton(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(id = R.drawable.ic_link),
                    imageSize = 23.dp,
                    color = White,
                    onClick = { onLink() }
                )
            }
        }
    }
}

