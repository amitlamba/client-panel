package com.und.controller

import com.und.eventapi.model.Event
import com.und.eventapi.model.EventUser
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/client/users")
class ClientUsersController {

    @RequestMapping(value = "/get-list", method = arrayOf(RequestMethod.GET))
    fun getListOfUsers(): List<EventUser> {
        //TODO Complete the method
        return listOf()
    }

    @RequestMapping(value = "/get-user-events", method = arrayOf(RequestMethod.GET))
    fun getEventsOfAUser(userID: String, emailID: String, undUserID: String): List<Event> {
        //TODO Complete the method
        return listOf()
    }
}