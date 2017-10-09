package com.und.model

import org.hibernate.validator.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size


class RegistrationRequest() {

    @NotNull
    @Email(message = "Email is not in valid format")
    lateinit var email: String

    @NotNull
    @Pattern(regexp = "^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*\\d)(?=\\S*[^\\w\\s])\\S{8,}\$", message = "password must contain one cap one small one number and one special")
    lateinit var password: String

    @NotNull
    @Size(min = 2, max = 30, message = "Name should contain 2 to 30 characters")
    lateinit var name: String

    lateinit var country: String

    lateinit var address: String

    var phone: String? = null

}
