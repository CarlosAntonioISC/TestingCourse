package com.android.testing.examples

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: Category,
    val isAvailable: Boolean,
    val stockQuantity: Int,
    val rating: Double,
    val reviewCount: Int
)

enum class Category {
    ELECTRONICS,
    CLOTHING,
    BOOKS,
    SPORTS
}

class ShoppingCart {
    private val items = mutableMapOf<Product, Int>()

    fun addProduct(product: Product, quantity: Int = 1): Boolean {
        if (!product.isAvailable || product.stockQuantity < quantity) {
            return false
        }

        val currentQuantity = items[product] ?: 0
        items[product] = currentQuantity + quantity
        return true
    }

    fun removeProduct(product: Product, quantity: Int): Boolean {
        val currentQuantity = items[product] ?: return false

        if (currentQuantity <= quantity) {
            items.remove(product)
        } else {
            items[product] = currentQuantity - quantity
        }
        return true
    }

    fun calculateTotal(): Double {
        return items.entries.sumOf { (product, quantity) ->
            product.price * quantity
        }
    }

}