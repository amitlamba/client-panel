package com.und.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.und.eventapi.repository.SegmentRepository
import com.und.model.jpa.Segment
import com.und.model.mongo.eventapi.EventUser
import com.und.repository.mongo.EventRepository
import com.und.security.utils.AuthenticationUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.und.web.model.Segment as WebSegment

@Service
class SegmentServiceImpl : SegmentService {


    @Autowired
    lateinit var segmentRepository: SegmentRepository

    @Autowired
    lateinit var eventRepository: EventRepository


    @Autowired
    private lateinit var objectMapper: ObjectMapper


    override fun createSegment(websegment: WebSegment): WebSegment {
        val segment = buildSegment(websegment)
        segmentRepository.save(segment)
        websegment.id = segment.id
        return websegment

    }

    override fun allSegment(): List<WebSegment> {
        val clientID = AuthenticationUtils.clientID
        val websegments = mutableListOf<WebSegment>()
        if (clientID != null) {
            val segments = segmentRepository.findByClientID(clientID)
            segments?.forEach { websegments.add(buildWebSegment(it)) }
        }

        return websegments
    }

    override fun segmentUsers(id: Long): List<EventUser> {
        val segmentOption = segmentRepository.findById(id)
        return if (segmentOption.isPresent) {
            val segment = segmentOption.get()
            buildWebSegment(segment)
            val queries = SegmentParserCriteria().segmentQueries(buildWebSegment(segment))
            queries.didq.forEach {
               val id =  eventRepository.usersFromEvent(it, 2)

            }
            emptyList()

        } else emptyList()
    }

    private fun buildSegment(websegment: WebSegment): Segment {
        val segment = Segment()
        with(segment) {
            id = websegment.id
            name = websegment.name
            type = websegment.type
            clientID = AuthenticationUtils.clientID
            appuserID = AuthenticationUtils.principal.id
            //FIXME create a separate class for json conversion
            data = objectMapper.writeValueAsString(websegment)
        }
        return segment
    }

    private fun buildWebSegment(segment: Segment): WebSegment {
        val websegment = objectMapper.readValue(segment.data, WebSegment::class.java)
        with(websegment) {
            id = segment.id
            name = segment.name
            type = segment.type
        }
        return websegment
    }
}