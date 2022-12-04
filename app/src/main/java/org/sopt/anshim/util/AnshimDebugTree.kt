package org.sopt.anshim.util

import timber.log.Timber

class AnshimDebugTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement) =
        "${element.fileName}:${element.lineNumber}#${element.methodName}"
}