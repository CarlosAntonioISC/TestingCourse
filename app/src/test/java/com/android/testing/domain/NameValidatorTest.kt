package com.android.testing.domain

import org.junit.Assert.*
import org.junit.Test

/**
 * - Test cases
 *
 * 1. ✅ "Juan"
 * 2. ✅ "Ana María"
 * 3. ❌ ""
 * 4. ❌ " "
 * 5. ❌ "A"
 * 6. ❌ "NameWithTooManyCharactersToBeValid"
 * 7. ❌ "John123"`
 * 8. ❌ "John_Doe"`
 */

class NameValidatorTest {

    private val nameValidator = NameValidator()


    @Test
    fun `Given a name with three characters when isValid then return true`() {
        val result = nameValidator.isValid("Ana")

        assertTrue(result)
    }

    @Test
    fun `Given a name with two characters when isValid then return false`() {
        val result = nameValidator.isValid("Li")

        assertFalse(result)
    }

    @Test
    fun `Given a name with spaces when isValid then return true`() {
        val result = nameValidator.isValid("Ana Maria")

        assertTrue(result)
    }

    @Test
    fun `Given an empty name when isValid then return false`() {
        val result = nameValidator.isValid("")

        assertFalse(result)
    }

    @Test
    fun `Given a blank name when isValid then return false`() {
        val result = nameValidator.isValid(" ")

        assertFalse(result)
    }

    @Test
    fun `Given a name with a single character when isValid then return false`() {
        val result = nameValidator.isValid("A")

        assertFalse(result)
    }

    @Test
    fun `Given a name with twenty one characters when isValid then return false`() {
        val result = nameValidator.isValid("Maximiliano Cristóbal")

        assertFalse(result)
    }

    @Test
    fun `Given a name with twenty characters when isValid then return true`() {
        val result = nameValidator.isValid("Alejandra Montserrat")

        assertTrue(result)
    }

    @Test
    fun `Given a name with numbers when isValid then return false`() {
        val result = nameValidator.isValid("John123")

        assertFalse(result)
    }

    @Test
    fun `Given a name with special characters when isValid then return false`() {
        val result = nameValidator.isValid("John_Doe")

        assertFalse(result)
    }

}