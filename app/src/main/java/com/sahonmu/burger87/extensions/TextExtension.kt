package com.sahonmu.burger87.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Base64
import com.google.gson.Gson
import java.text.DecimalFormat


fun String.copy(context: Context) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", this)
    clipboard.setPrimaryClip(clip)
}

fun Number.prettyCount(): String {
    return DecimalFormat("###,###").format(this)
}
