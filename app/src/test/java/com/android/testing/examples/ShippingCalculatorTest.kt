package com.android.testing.examples

import org.junit.Assert.assertEquals
import org.junit.Test

class ShippingCalculatorTest {

    private val suv = ShippingCalculator()

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw exception for negative distance`() {
        suv.calculateFee(-5.0, 5.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw exception for negative weight`() {
        suv.calculateFee(5.0, -5.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw exception for zero distance`() {
        suv.calculateFee(0.0, -5.0)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Should throw exception for zero weight`() {
        suv.calculateFee(7.0, 0.0)
    }

    @Test
    fun `Given 1km when the weight is free of fee then just add base and distance fee`() {
        val result = suv.calculateFee(1.0, 5.0)
        assertEquals(5.5, result, 0.0)
    }

    @Test
    fun `Given 10km when the weight is free of fee then just add base and distance fee`() {
        val result = suv.calculateFee(10.0, 7.0)
        assertEquals(10.0, result, 0.0)
    }

    @Test
    fun `Given 1km when the weight exceed max weight then add base, distance and weight fee`() {
        val result = suv.calculateFee(1.0, 15.0)
        assertEquals(13.0, result, 0.0)
    }

    @Test
    fun `Given 10km when the weight exceed max weight then add base, distance and weight fee`() {
        val result = suv.calculateFee(10.0, 17.0)
        assertEquals(20.5, result, 0.0)
    }


}