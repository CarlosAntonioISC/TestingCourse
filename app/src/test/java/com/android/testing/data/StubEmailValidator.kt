package com.android.testing.data

import com.android.testing.domain.EmailValidator

class StubEmailValidator: EmailValidator {

    override fun isValid(email: String): Boolean {
        return email == VALID_EMAIL
    }

    companion object {
        const val VALID_EMAIL = "test@gmail.com"
    }

}