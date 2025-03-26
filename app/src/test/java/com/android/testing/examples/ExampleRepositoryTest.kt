package com.android.testing.examples

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ExampleRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()
    private val exampleRepository = ExampleRepository(testDispatcher)

    @Test
    fun testDispatchers() = runTest(testDispatcher.scheduler) {

        StandardTestDispatcher(testDispatcher.scheduler)

        exampleRepository.fetchData()
    }

    @Test
    fun testDispatchers2() = runTest {
        val exampleRepository = ExampleRepository(StandardTestDispatcher(testScheduler))
        exampleRepository.fetchData()
    }

}