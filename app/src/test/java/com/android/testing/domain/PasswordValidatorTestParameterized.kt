package com.android.testing.domain

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PasswordValidatorTestParameterized(
    private val password: String,
    private val expected: Boolean
) {

    private val sut = PasswordValidator()

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf("Password1!", true),
                arrayOf("Pass1!", false),
                arrayOf("PASSWORD1!", false),
                arrayOf("password1!", false),
                arrayOf("Password!", false),
                arrayOf("Password123", false)
            )
        }
    }

   @Test
   fun `Validate passwords`() {
       assertEquals(expected, sut.isValid(password))
   }

}