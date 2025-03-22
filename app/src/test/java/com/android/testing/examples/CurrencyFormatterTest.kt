package com.android.testing.examples

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class CurrencyFormatterTest(
    private val amount: Int,
    private val currency: String,
    private val expected: String
) {

    private val suv = CurrencyFormatter()

    companion object {
        @JvmStatic
        @Parameters
        fun getData(): List<Array<Any>> {
            return listOf(
                arrayOf(100, "USD", "$100.00"),
                arrayOf(7, "EUR", "7.00€"),
                arrayOf(60, "GBP", "£60.00"),
                arrayOf(1, "JPY", "¥1"),
                arrayOf(42, "TEST", "42.00 TEST"),
            )
        }
    }

    @Test
    fun `Validate currency format`() {
        val result = suv.format(amount, currency)
        assertEquals(expected, result)
    }

}