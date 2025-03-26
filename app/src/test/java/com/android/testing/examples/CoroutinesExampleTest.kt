package com.android.testing.examples

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

class CoroutinesExampleTest {

    @Test
    fun `Test delay`() = runTest {
        delay(5.seconds)
        assertTrue(true)
    }

    @Test
    fun `Test launch`() = runTest {
        var result = false
        launch {
            result = true
        }
        testScheduler.advanceUntilIdle()
        assertTrue(result)
    }

}