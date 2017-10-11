package com.und.exception

import com.und.model.validation.ValidationError

class UndBusinessValidationException (
        var error: ValidationError
) : Exception() {


}

