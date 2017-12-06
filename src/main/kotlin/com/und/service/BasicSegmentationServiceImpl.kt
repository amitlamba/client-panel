package com.und.service

import com.und.eventapi.model.EventUser
import com.und.eventapi.model.SocialId
import com.und.eventapi.model.StandardInfo
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
        eventUser.standardInfo = StandardInfo(firstName = "Amit", lastName = "Lamba", country = "India", countryCode = "IN",
                dob = "2017-01-04",gender = "Male")
        eventUser.socialId = SocialId(email = "amit@userndot.com", mobile = "8882774104")
        eventUser.id=null

        return eventUser
    }
}