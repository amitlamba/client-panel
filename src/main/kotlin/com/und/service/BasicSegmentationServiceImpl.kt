package com.und.service

import com.und.eventapi.model.EventUser
import com.und.model.Segmentation
import org.springframework.stereotype.Service

@Service
class BasicSegmentationServiceImpl : SegmentationService {
    override fun getSegmentationUsers(clientID: Long, segmentation: Segmentation?): List<EventUser> {
        return listOf(element = getDummyEventUser())
    }

    fun getDummyEventUser(): EventUser {
        val eventUser = EventUser()
        eventUser.clientId ="1"
        eventUser.clientUserId="1"
        eventUser.deviceId = arrayListOf()
        eventUser.email = "amit@userndot.com"
        eventUser.firstname = "Amit"
        eventUser.id=null
        eventUser.isIdentified=true
        eventUser.lastname = "Lamba"
        eventUser.mobile="8882774104"
        eventUser.undUserId="1"

        return eventUser
    }
}