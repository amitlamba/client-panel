package com.und.web.controller

import com.und.model.mongo.eventapi.Event
import com.und.model.mongo.eventapi.EventUser
import com.und.security.utils.AuthenticationUtils
import com.und.service.ClientUsersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/client/users")
class ClientUsersController {

    @Autowired
    private lateinit var clientUsersService: ClientUsersService

    @RequestMapping(value = "/get-list", method = arrayOf(RequestMethod.GET))
    fun getListOfUsers(): List<EventUser> {
        return clientUsersService.getEventUsers(AuthenticationUtils.clientID!!)
    }

    @RequestMapping(value = "/get-user-events", method = arrayOf(RequestMethod.GET))
    fun getEventsOfAUser(userID: String, emailID: String, undUserID: String): List<Event> {
        //TODO Complete the method
        return listOf()
    }
}