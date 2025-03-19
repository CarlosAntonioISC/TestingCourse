package com.android.testing.domain

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AgeValidatorTest {

    @Test
    fun `Given valid age when isValid then returns true`() {
        //ARRANGE
        val ageValidator = AgeValidator()

        //ACT
        val result = ageValidator.isValid(20)

        //ASSERT
        assertTrue(result)
    }

    @Test
    fun `Given age below minimum when isValid then returns false`() {
        //ARRANGE
        val ageValidator = AgeValidator()

        //ACT
        val result = ageValidator.isValid(17)

        //ASSERT
        assertFalse(result)
    }

    @Test
    fun `Given age above maximum when isValid then returns false`() {
        //ARRANGE
        val ageValidator = AgeValidator()

        //ACT
        val result = ageValidator.isValid(66)

        //ASSERT
        assertFalse(result)
    }

    @Test
    fun `Given negative age when isValid then returns false`() {
        //ARRANGE
        val ageValidator = AgeValidator()

        //ACT
        val result = ageValidator.isValid(-1)

        //ASSERT
        assertFalse(result)
    }

}