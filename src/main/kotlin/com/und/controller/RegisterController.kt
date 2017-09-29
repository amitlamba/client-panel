package com.und.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import javax.validation.Valid

@Controller
class RegisterController {

    @RequestMapping(value="/register", method = arrayOf(RequestMethod.GET) )
    fun registerForm(){

    }

    @RequestMapping(value="/register", method= arrayOf(RequestMethod.POST))
    fun register(@Valid @RequestBody request : String){

    }
}