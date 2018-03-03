package com.und.service

import com.und.eventapi.model.EventUser
import com.und.eventapi.model.SocialId
import com.und.eventapi.model.StandardInfo
import com.und.model.Segmentation
import com.und.model.api.Segment
import org.springframework.stereotype.Service

@Service
class SegmentServiceImpl : SegmentService {
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

    override fun createSegment(segment: Segment): Segment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        // save in segment db

    }
}