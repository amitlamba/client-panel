package com.und.model

import org.hibernate.validator.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size


class RegistrationRequest() {

    @NotNull
    @Email
    lateinit var email : String

    @NotNull
    @Size(min=10, max=15)
    lateinit var password : String

    @NotNull
    @Size(min=2, max=30)
    lateinit var name : String

    @NotNull
    lateinit var country : String

    @NotNull
    lateinit var Address : String
}
