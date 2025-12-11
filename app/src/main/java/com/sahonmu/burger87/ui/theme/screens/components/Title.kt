package com.sahonmu.burger87.ui.theme.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@Composable
fun Title(
    modifier: Modifier = Modifier,
    title: String,
    onBack: () -> Unit = { }
) {

    ConstraintLayout(
        modifier = modifier.fillMaxWidth().size(56.dp),
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

    var checked by remember { mutableStateOf(true) }

    ConstraintLayout(
        modifier = modifier.fillMaxWidth().size(56.dp),
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
            Text(
                text = "폐점 포함",
                fontSize = 13.sp,
                color = Base
            )
            Checkbox(
                modifier = Modifier.size(30.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = Base
                ),
                checked = checked,
                onCheckedChange = {
                    checked = it
                    onCheck(checked)
                }
            )
            WidthMargin(10.dp)
        }
    }

}


@Preview
@Composable
fun StoreDetailTitlePreview() {
//    StoreDetailScreen(rememberNavController())
}

