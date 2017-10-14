package com.und.model.api

class Response {
    var status: ResponseStatus = ResponseStatus.EMPTY
    var data: Data = Data()
    var message: String? = null
    var validationError: ValidationError? = null

}

class Data() {
    var value: Any? = null
    var message: String? = null
}