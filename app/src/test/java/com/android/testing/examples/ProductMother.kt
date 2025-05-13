package com.android.testing.examples

import com.android.testing.util.RandomValuesUtil

object ProductMother {

    fun productNotAvailable() = apply(isAvailable = false)

    fun apply(
        id: Int = RandomValuesUtil.getInt(),
        name: String = RandomValuesUtil.getString(),
        price: Double = RandomValuesUtil.getDouble(),
        category: Category = Category.entries.toTypedArray().random(),
        isAvailable: Boolean = RandomValuesUtil.getBoolean(),
        stockQuantity: Int = RandomValuesUtil.getInt(),
        rating: Double = RandomValuesUtil.getDouble(),
        reviewCount: Int = RandomValuesUtil.getInt()
    ) = Product(
        id = id,
        name = name,
        price = price,
        category = category,
        isAvailable = isAvailable,
        stockQuantity = stockQuantity,
        rating = rating,
        reviewCount = reviewCount
    )

    fun random() = apply()

}