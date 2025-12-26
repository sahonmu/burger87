package com.sahonmu.burger87.ui.theme.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.sahonmu.burger87.ui.theme.Base
import com.sahonmu.burger87.ui.theme.Black
import com.sahonmu.burger87.ui.theme.Body_3
import com.sahonmu.burger87.ui.theme.Gray_50
import com.sahonmu.burger87.ui.theme.Gray_600
import com.sahonmu.burger87.ui.theme.Label_1
import com.sahonmu.burger87.ui.theme.Title_2
import com.sahonmu.burger87.ui.theme.White


@Composable
fun Alert(
    title: String = "",
    message: String = "",
    positiveButtonText: String = "확인",
    dismissOnClickOutside: Boolean = false,
    dismissOnBackPress: Boolean = true,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnClickOutside = dismissOnClickOutside,
            dismissOnBackPress = dismissOnBackPress,
        )
    ) {
        AlertContent(
            title,
            message,
            positiveButtonText,
            onDismissRequest = {
                onDismissRequest()
            }
        )
    }
}

@Composable
fun Confirm(
    title: String = "",
    message: String = "",
    positiveButtonText: String = "확인",
    negativeButtonText: String = "취소",
    dismissOnClickOutside: Boolean = true,
    dismissOnBackPress: Boolean = true,
    onPositive: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnClickOutside = dismissOnClickOutside,
            dismissOnBackPress = dismissOnBackPress,
        )
    ) {
        ConfirmContent(
            title = title,
            message = message,
            positiveButtonText = positiveButtonText,
            negativeButtonText = negativeButtonText,
            onPositive = onPositive,
            onDismissRequest = onDismissRequest,
        )
    }
}


@Composable
fun AlertContent(
    title: String = "",
    message: String = "",
    positiveButtonText: String = "확인",
    onDismissRequest: () -> Unit,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 192.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
    ) {

        val (body, footer) = createRefs()

        Box(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .constrainAs(body) {
                    height = Dimension.fillToConstraints
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = footer.top)
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = Black,
                        style = Title_2
                    )
                }
                if (message.isNotEmpty()) {
                    Text(
                        text = message,
                        color = Black,
                        style = Body_3,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
//        Margin(modifier =  Modifier.weight(1f))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(footer) {
                    top.linkTo(anchor = body.bottom)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = parent.bottom)
                },
            shape = RoundedCornerShape(bottomStart = 18.dp, bottomEnd = 18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Base
            ),
            onClick = {
                onDismissRequest()
            }
        ) {
            Text(
                text = positiveButtonText,
                color = White,
                style = Label_1
            )
        }
    }
}

@Composable
fun ConfirmContent(
    title: String = "",
    message: String = "",
    positiveButtonText: String = "확인",
    negativeButtonText: String = "취소",
    onPositive: () -> Unit,
    onDismissRequest: () -> Unit,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
//            .defaultMinSize(minHeight = 192.dp)
            .height(if (title.isNotEmpty() && message.toCharArray().count { it == '\n' } >= 2) 216.dp else 192.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.White)
    ) {
        val (body, footer) = createRefs()

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .constrainAs(body) {
                    height = Dimension.fillToConstraints
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = footer.top)
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        color = Black,
                        style = Title_2
                    )
                }
                if (message.isNotEmpty()) {
                    Text(
                        text = message,
                        color = Black,
                        style = Body_3,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .constrainAs(footer) {
                    top.linkTo(anchor = body.bottom)
                    start.linkTo(anchor = parent.start)
                    end.linkTo(anchor = parent.end)
                    bottom.linkTo(anchor = parent.bottom)
                },
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(bottomStart = 18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Gray_50,
                ),
                onClick = { onDismissRequest() }
            ) {
                Text(
                    text = negativeButtonText,
                    color = Gray_600,
                    style = Label_1
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(bottomEnd = 18.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Base
                ),
                onClick = { onPositive() }
            ) {
                Text(
                    text = positiveButtonText,
                    color = White,
                )
            }
        }
    }
}

@Composable
fun ProgressDialog(
    showProgress: Boolean = false
) {
    if (showProgress) {
        Dialog(
            onDismissRequest = {  },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            CircularProgressIndicator(
                color = Base
            )
        }
    }
}