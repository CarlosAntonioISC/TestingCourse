package com.android.testing.examples

import org.junit.Assert.*
import org.junit.Test

class SlugGeneratorTest {

    private val sut = SlugGenerator()

    @Test
    fun `Should convert text to lowercase and replace spaces`() {
        val result = sut.generateSlug("Hello World")
        assertEquals("hello-world", result)
    }

    @Test
    fun `Should remove special characters`() {
        val result = sut.generateSlug("Kotlin! is #1?")
        assertEquals("kotlin-is-1", result)
    }

    @Test
    fun `Should trim spaces`() {
        val result = sut.generateSlug("  Android Kotlin  ")
        assertEquals("android-kotlin", result)
    }

    @Test
    fun `Should handle empty input`() {
        val result = sut.generateSlug("")
        assertEquals("", result)
    }

}