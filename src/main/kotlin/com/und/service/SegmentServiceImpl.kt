package com.und.service

import com.google.gson.Gson
import com.und.eventapi.repository.SegmentRepository
import com.und.model.Segment
import com.und.security.utils.AuthenticationUtils
import com.und.model.api.Segment as WebSegment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SegmentServiceImpl : SegmentService {

    @Autowired
    lateinit var segmentRepository: SegmentRepository

/*    override fun getSegmentationUsers(clientID: Long, segmentation: Segmentation?): List<EventUser> {
        return listOf(element = getDummyEventUser())
    }*/

/*    fun getDummyEventUser(): EventUser {
        val eventUser = EventUser()
        eventUser.clientId ="1"
        eventUser.clientUserId="1"
        eventUser.standardInfo = StandardInfo(firstName = "Amit", lastName = "Lamba", country = "India", countryCode = "IN",
                dob = "2017-01-04",gender = "Male")
        eventUser.socialId = SocialId(email = "amit@userndot.com", mobile = "8882774104")
        eventUser.id=null

        return eventUser
    }*/

    override fun createSegment(websegment: WebSegment): WebSegment {
        val segment = buildSegment(websegment)
        segmentRepository.save(segment)
        websegment.id = segment.id
        return websegment

    }

    private fun buildSegment(websegment: WebSegment): Segment {
        val segment = Segment()
        with(segment) {
            id = websegment.id
            name = websegment.name
            type = websegment.type
            clientID = AuthenticationUtils.clientID
            //data = Gson.
        }
        return segment
    }

    private fun buildWebSegment(segment: Segment): WebSegment {
        val websegment = WebSegment()
        with(websegment) {
            id = segment.id
            name = segment.name
            type = segment.type
        }
        return websegment
    }
}