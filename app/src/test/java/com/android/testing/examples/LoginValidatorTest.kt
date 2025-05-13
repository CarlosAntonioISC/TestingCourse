package com.android.testing.examples

import org.junit.Assert.assertTrue
import org.junit.Test

class LoginValidatorTest {

    @Test
    // ❌
    fun `email and password are valid returns true and no errors`() {
        val validator = LoginValidator()

        val result = validator.validate("user@example.com", "123456")

        assertTrue(result.isValid)
        assertTrue(result.errors.isEmpty())
    }

    @Test
    // ✅
    fun `valid credentials returns isValid true`() {
        val validator = LoginValidator()

        val result = validator.validate("user@example.com", "123456")

        assertTrue(result.isValid)
    }

    @Test
    // ✅
    fun `valid credentials returns no errors`() {
        val validator = LoginValidator()

        val result = validator.validate("user@example.com", "123456")

        assertTrue(result.errors.isEmpty())
    }

}