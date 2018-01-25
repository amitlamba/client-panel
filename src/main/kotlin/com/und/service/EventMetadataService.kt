package com.und.service

import com.und.model.mongo.EventMetadata
import org.springframework.stereotype.Service

@Service
class EventMetadataService {
    fun getEventMetadata(): List<EventMetadata> {
        return mutableListOf()
    }
}