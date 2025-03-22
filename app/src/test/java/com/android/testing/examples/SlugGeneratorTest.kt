package com.android.testing.examples

import org.junit.Assert.*
import org.junit.Test

class SlugGeneratorTest {

    private val suv = SlugGenerator()

    @Test
    fun `Should convert text to lowercase and replace spaces`() {
        val result = suv.generateSlug("Hello World")
        assertEquals("hello-world", result)
    }

    @Test
    fun `Should remove special characters`() {
        val result = suv.generateSlug("Kotlin! is #1?")
        assertEquals("kotlin-is-1", result)
    }

    @Test
    fun `Should trim spaces`() {
        val result = suv.generateSlug("  Android Kotlin  ")
        assertEquals("android-kotlin", result)
    }

    @Test
    fun `Should handle empty input`() {
        val result = suv.generateSlug("")
        assertEquals("", result)
    }

}