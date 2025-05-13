package com.android.testing.examples

class ExampleClass {

    // This function exists only to use the private function isEven
    fun describeNumber(number: Int): String {
        return if (isEven(number)) "Even" else "Odd"
    }

    //Es par?
    private fun isEven(number: Int): Boolean {
        return number % 2 == 0
    }

}