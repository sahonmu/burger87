package com.sahonmu.burger87.utils.log

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri

object IntentUtils {

    fun startActivityForInstagram(context: Context, uri: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(uri)
            setPackage("com.instagram.android") // 앱에서 열기
        }

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // 앱이 설치 안 되어있으면 브라우저로 열기
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            context.startActivity(webIntent)
        }
    }

    fun startActivityForDialog(context: Context, number: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$number")
        }
        context.startActivity(intent)
    }

}