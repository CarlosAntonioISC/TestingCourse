package com.android.testing.examples

data class ValidationResult(
    val isValid: Boolean,
    val errors: List<String>
)

class LoginValidator {

    fun validate(email: String, password: String): ValidationResult {
        val errors = mutableListOf<String>()
        if (!email.contains("@")) errors.add("Invalid email")
        if (password.length < 6) errors.add("Password too short")

        return ValidationResult(errors.isEmpty(), errors)
    }
    
}