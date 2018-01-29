package com.und.controller

import com.und.model.mongo.EventMetadata
import com.und.service.EventMetadataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController("segment")
@RequestMapping("/segment")
class SegmentController {

    @Autowired
    private lateinit var eventMetadataService: EventMetadataService

    @RequestMapping(value = "/metadata", method = arrayOf(RequestMethod.GET))
    fun getEventMetadta(): List<EventMetadata> {
        return eventMetadataService.getEventMetadata()
    }

    fun getCommonProperties() {

    }


}