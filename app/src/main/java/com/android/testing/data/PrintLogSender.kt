package com.android.testing.data

import com.android.testing.examples.LogSender

class PrintLogSender: LogSender {

    override fun send(message: String) {
        println(message)
    }

}