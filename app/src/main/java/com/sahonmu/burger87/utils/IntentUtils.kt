package com.sahonmu.burger87.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.core.net.toUri

object IntentUtils {

    fun startActivityForInstagram(context: Context, uri: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = uri.toUri()
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
            data = "tel:$number".toUri()
        }
        context.startActivity(intent)
    }


    fun startActivityForShare(context: Context, content: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, content)
        }
        context.startActivity(intent)
    }

    fun startActivityForBurgerReport(context: Context) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf("sahonmu@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "버거맛집 추천합니다!!!")
            putExtra(Intent.EXTRA_TEXT, "버거상호명 :\n지점 :")
        }
        context.startActivity(intent)
    }


    fun startActivityReportData(context: Context) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf("sahonmu@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "정보변경 및 삭제 요청")
            putExtra(Intent.EXTRA_TEXT, "정보변경 상점및 지점 :\n정보변경 내용 : ")
        }
        context.startActivity(intent)
    }

    fun startActivityForGooglePlay(context: Context) {
        val pkg = context.packageName
        val intent = Intent(Intent.ACTION_VIEW, "market://details?id=$pkg".toUri())
        context.startActivity(intent)
    }


    fun startActivityBrowser(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        context.startActivity(intent)
    }

    fun startActivityOpenAppSetting(context: Context) {
        val intent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//            Settings.ACTION_LOCATION_SOURCE_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        )
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

}