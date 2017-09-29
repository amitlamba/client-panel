package com.und.model

import org.jetbrains.annotations.NotNull

data class RegistrationRequest(

    @NotNull
    val email : String,
    val password : String
)
