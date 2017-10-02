package com.und.controller

import com.und.common.utils.loggerFor
import com.und.model.ValidationError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus



/**
 * @author Petri Kainulainen
 */
@ControllerAdvice
class RestErrorHandler {

    companion object {

        protected val logger = loggerFor(javaClass)
    }

    @Autowired
    lateinit var messageSource: MessageSource


    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun processValidationError(ex: MethodArgumentNotValidException): ValidationError {
        logger.debug("Handling form validation error")

        val result = ex.bindingResult
        val fieldErrors = result.fieldErrors

        return processFieldErrors(fieldErrors)
    }

    private fun processFieldErrors(fieldErrors: List<FieldError>): ValidationError {
        val dto = ValidationError()

        for (fieldError in fieldErrors) {
            val localizedErrorMessage = resolveLocalizedErrorMessage(fieldError)
            logger.debug("Adding error message: {} to field: {}", localizedErrorMessage, fieldError.field)
            dto.addFieldError(fieldError.field, localizedErrorMessage)
        }

        return dto
    }

    private fun resolveLocalizedErrorMessage(fieldError: FieldError): String {
        val currentLocale = LocaleContextHolder.getLocale()
        var localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale)

        //If a message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage == fieldError.defaultMessage) {
            val fieldErrorCodes = fieldError.codes
            localizedErrorMessage = fieldErrorCodes[0]
        }

        return localizedErrorMessage
    }
}
