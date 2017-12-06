package com.und.service

import com.und.eventapi.model.Event
import com.und.eventapi.model.EventUser
import com.und.eventapi.model.Identity
import com.und.eventapi.repository.EventRepository
import com.und.eventapi.repository.EventUserRepository
import com.und.security.utils.TenantProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientUsersService {

    @Autowired
    private lateinit var eventUserRepository: EventUserRepository
    @Autowired
    private lateinit var eventRepository: EventRepository

    fun getEventUsers(clientId: Long): List<EventUser> {
        TenantProvider().setTenat(clientId.toString())
        val eventUsers = eventUserRepository.findAll()
        return eventUsers
    }

    fun getEventUsersEvents(clientId: Long, userId: String, clientUserId: String): List<Event> {
        TenantProvider().setTenat(clientId.toString())
        val events = eventRepository.findByIdentity(Identity(userId = userId))
        return events
    }
}