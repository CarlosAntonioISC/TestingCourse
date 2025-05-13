package com.android.testing.examples

import org.junit.Assert.*
import org.junit.Test
import java.lang.reflect.Method
import kotlin.random.Random

class ExampleClassTest {

    @Test
    fun codeSmellsInTest() {
        val sut = ExampleClass()

        // ❌ 1. Usar reflexión para testear función privada directamente
        val privateMethod: Method =
            ExampleClass::class.java.getDeclaredMethod("isEven", Int::class.java)
        privateMethod.isAccessible = true
        privateMethod.invoke(sut, 4) as Boolean

        // ❌ 2. If/else con distintas aserciones dentro del mismo test
        val number = Random.nextInt(10)
        if (number % 2 == 0) {
            assertEquals("Even", sut.describeNumber(number))
        } else {
            assertEquals("Odd", sut.describeNumber(number))
        }
    }

}