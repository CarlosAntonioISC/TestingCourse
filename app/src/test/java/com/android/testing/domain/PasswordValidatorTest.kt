package com.android.testing.domain

import org.junit.Assert.*
import org.junit.Test

/**
 * - Test cases
 *
 * 1. ✅ "Password1!"
 * 3. ❌ "Pass1!" very short
 * 4. ❌ "PASSWORD1!" without lowercase letter
 * 5. ❌ "password1!" without uppercase letter
 * 6. ❌ "Password!" without any number
 * 7. ❌ "Password123" without special character
 */

class PasswordValidatorTest {

    private val suv = PasswordValidator()

    @Test
    fun `Given a password with uppercase, lowercase, digit, and number then isValid return true`() {
        val result = suv.isValid("Password1!")

        assertTrue(result)
    }

    @Test
    fun `Given a short password then isValid return false`() {
        val result = suv.isValid("Pass1!")

        assertFalse(result)
    }

    @Test
    fun `Given a password without lowercase then isValid return false`() {
        val result = suv.isValid("PASSWORD1!")

        assertFalse(result)
    }

    @Test
    fun `Given a password without uppercase then isValid return false`() {
        val result = suv.isValid("password1!")

        assertFalse(result)
    }

    @Test
    fun `Given a password without number then isValid return false`() {
        val result = suv.isValid("Password!")

        assertFalse(result)
    }

    @Test
    fun `Given a password without special character then isValid return false`() {
        val result = suv.isValid("Password123")

        assertFalse(result)
    }


}