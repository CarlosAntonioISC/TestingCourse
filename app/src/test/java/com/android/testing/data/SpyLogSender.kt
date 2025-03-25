package com.android.testing.data

import com.android.testing.examples.LogSender

class SpyLogSender: LogSender {

    var counter = 0

    override fun send(message: String) {
        counter++
    }

}