package com.android.testing.util

import kotlin.random.Random

internal object RandomValuesUtil {

    fun getString(length: Int = 10): String {
        val allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun getInt(min: Int = 0, max: Int = 100): Int {
        return Random.nextInt(from = min, until = max)
    }

    fun getDouble(min: Double = 0.0, max: Double = 100.0): Double {
        return Random.nextDouble(from = min, until = max)
    }

    fun getBoolean(): Boolean {
        return Random.nextBoolean()
    }

    fun getRandomIntRange(min: Int = 0, max: Int = 100): IntRange {
        return (min..max)
    }

}