package com.und.controller

import com.und.model.mongo.EventMetadata
import com.und.service.EventMetadataService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController("/segment")
class SegmentController {

    private lateinit var eventMetadataService: EventMetadataService

    @RequestMapping(value = "/metadata", method = arrayOf(RequestMethod.GET))
    fun getEventMetadta(): List<EventMetadata> {
        //TODO define eventmetadta mongo object
        //define service to fetch metadata
        return eventMetadataService.getEventMetadata()
    }

    fun getCommonProperties() {

    }


}