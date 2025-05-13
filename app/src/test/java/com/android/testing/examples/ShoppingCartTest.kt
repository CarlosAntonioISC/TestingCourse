package com.android.testing.examples

import org.junit.Assert.*
import org.junit.Test

class ShoppingCartTest {

    @Test
    fun `Given a product that is not available when addProduct then returns false`() {
        val product = ProductMother.productNotAvailable()
        val cart = ShoppingCart()
        val result = cart.addProduct(product)

        assertFalse(result)
    }

    @Test
    fun `Given a product that is available when addProduct then returns true`() {
        val product = ProductMother.apply(isAvailable = true, stockQuantity = 10)
        val cart = ShoppingCart()
        val result = cart.addProduct(product)

        assertTrue(result)
    }

}