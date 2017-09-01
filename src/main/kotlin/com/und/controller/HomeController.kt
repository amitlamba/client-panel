/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.und.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 *
 * @author amit
 */
@Controller
class HomeController {

    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/hello")
    fun renderHelloWorld(model: Model, @RequestParam(value = "name", required = false, defaultValue = "World") name: String): String {
        model.addAttribute("name", name)
        return "hello"
    }
}
