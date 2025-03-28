package com.android.testing.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ExampleClassIteration {
    var iteration: Int = 10
}

class ExampleClassIterationTest {

    private val sut = ExampleClassIteration()

    @Test
    fun test1() {
        sut.iteration++
        assertEquals(11, sut.iteration)
    }

    @Test
    fun test2() {
        sut.iteration--
        sut.iteration--
        assertEquals(8, sut.iteration)
    }

    @Test
    fun test3() {
        sut.iteration--
        sut.iteration--
        sut.iteration--
        assertEquals(7, sut.iteration)
    }

    @Test
    fun test4() {
        sut.iteration++
        sut.iteration++
        assertEquals(12, sut.iteration)
    }

    @Test
    fun test5() {
        sut.iteration++
        sut.iteration++
        assertEquals(12, sut.iteration)
    }

}