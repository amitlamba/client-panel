package com.und.model

import java.util.ArrayList

/**
 * @author Petri Kainulainen
 */
class ValidationError {

    private val fieldErrors = ArrayList<FieldError>()

    fun addFieldError(path: String, message: String) {
        val error = FieldError(path, message)
        fieldErrors.add(error)
    }

    fun getFieldErrors(): List<FieldError> {
        return fieldErrors
    }
}
