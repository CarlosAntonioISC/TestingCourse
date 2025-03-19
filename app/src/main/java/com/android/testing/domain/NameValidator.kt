package com.android.testing.domain

class NameValidator {

    fun isValid(name: String): Boolean {
        if (name.isBlank()) return false
        if (name.length !in MIN_CHARACTERS..MAX_CHARACTERS) return false
        if (!name.all { it.isLetter() || it.isWhitespace() }) return false
        return true
    }

    companion object {
        private const val MIN_CHARACTERS = 3
        private const val MAX_CHARACTERS = 20
    }

}
