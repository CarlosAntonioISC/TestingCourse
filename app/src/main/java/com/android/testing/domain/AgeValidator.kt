package com.android.testing.domain

class AgeValidator {

    fun isValid(age: Int): Boolean {
        return age in MIN_AGE_VALID..MAX_AGE_VALID
    }

    companion object {
        private const val MIN_AGE_VALID = 18
        private const val MAX_AGE_VALID = 65
    }

}
