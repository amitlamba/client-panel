package com.und.service

import com.und.eventapi.model.EventUser
import com.und.model.Segmentation
import org.springframework.stereotype.Service

@Service
interface SegmentationService {
    fun getSegmentationUsers(clientID: Long, segmentation: Segmentation? = null): List<EventUser>
}