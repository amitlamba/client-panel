package com.und.service

import com.und.eventapi.model.EventUser
import com.und.model.Segmentation
import com.und.model.api.Segment
import org.springframework.stereotype.Service

@Service
interface SegmentService {
    fun getSegmentationUsers(clientID: Long, segmentation: Segmentation? = null): List<EventUser>

    fun createSegment(segment: Segment): Segment
}