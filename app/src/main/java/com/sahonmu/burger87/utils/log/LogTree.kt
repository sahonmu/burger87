package com.sahonmu.burger87.utils.log

import timber.log.Timber

class LogTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "BURGER87/${element.fileName}:${element.lineNumber}#${element.methodName}"
    }
}