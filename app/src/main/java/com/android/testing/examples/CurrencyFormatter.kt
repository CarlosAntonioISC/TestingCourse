package com.android.testing.examples

class CurrencyFormatter {
    /**
     * Formatea un valor numérico como moneda según el código de moneda especificado.
     *
     * @param amount El valor a formatear.
     * @param currencyCode El código de moneda ISO (USD, EUR, etc.).
     * @return Cadena formateada según las reglas de moneda.
     */
    fun format(amount: Int, currencyCode: String): String {
        return when (currencyCode.uppercase()) {
            "USD" -> "\$%d.00".format(amount)
            "EUR" -> "%d.00€".format(amount)
            "GBP" -> "£%d.00".format(amount)
            "JPY" -> "¥%d".format(amount)
            else -> "%d.00 ${currencyCode.uppercase()}".format(amount)
        }
    }

}