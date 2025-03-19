package com.android.testing.domain

class PasswordValidator {

    fun isValid(password: String): Boolean {
        if (password.length < MIN_LENGTH_PASSWORD) return false
        if (!password.any { it.isUpperCase() }) return false
        if (!password.any { it.isLowerCase() }) return false
        if (!password.any { it.isDigit() }) return false
        if (password.any { it in VALID_SPECIAL_CHARACTERS }) return false

        return true
    }

    companion object {
        private const val MIN_LENGTH_PASSWORD = 8
        private const val VALID_SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?/{}[]"
    }

}