package com.und.service

import com.und.eventapi.repository.EventMetadataRepository
import com.und.model.mongo.EventMetadata
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EventMetadataService {

    @Autowired
    lateinit var eventMetadataRepository: EventMetadataRepository

    fun getEventMetadata(): List<EventMetadata> {
        return eventMetadataRepository.findAll()
    }
}