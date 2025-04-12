package com.android.testing.domain

import android.util.Patterns

class AndroidEmailValidator: EmailValidator {

    override fun isValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

}

interface EmailValidator {

    fun isValid(email: String): Boolean

}