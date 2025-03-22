package com.android.testing.examples

/**
 * ¿Qué hace esta clase?
 *
 * Calcula la tarifa de envío en función de la distancia y el peso.
 * Aplica una tarifa base de 5.0.
 * Suma 0.5 por cada km.
 * Si el peso supera 10kg, cobra 1.5 extra por cada kg adicional.
 * Lanza excepciones si los valores son inválidos (negativos o 0).
 *
 * 📌 Reglas del cálculo
 * Tarifa base: $5.0
 * Costo por distancia: $0.5 por cada kilómetro
 * Costo por peso:
 * Hasta 10kg, no hay costo extra.
 * Si el peso supera 10kg, se cobra $1.5 por cada kg extra.
 *
 */

class ShippingCalculator {

    fun calculateFee(distanceKm: Double, weightKg: Double): Double {
        require(distanceKm > 0) { "Distance must be greater than 0" }
        require(weightKg > 0) { "Weight must be greater than 0" }

        val distanceFee = distanceKm * DISTANCE_FEE
        val weightFee = if (weightKg > MAX_WEIGHT_WITHOUT_EXTRA_FEE) {
            (weightKg - MAX_WEIGHT_WITHOUT_EXTRA_FEE) * WEIGHT_FEE
        } else {
            0.0
        }

        return BASE_FEE + distanceFee + weightFee
    }

    companion object {
        private const val BASE_FEE = 5.0
        private const val DISTANCE_FEE = 0.5
        private const val WEIGHT_FEE = 1.5
        private const val MAX_WEIGHT_WITHOUT_EXTRA_FEE = 10.0
    }

}