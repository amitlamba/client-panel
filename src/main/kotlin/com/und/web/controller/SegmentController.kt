package com.und.web.controller

import com.und.model.mongo.CommonMetadata
import com.und.model.mongo.EventMetadata
import com.und.service.EventMetadataService
import com.und.service.SegmentService
import com.und.web.model.Segment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("segment")
@RequestMapping("/segment")
class SegmentController {

    @Autowired
    private lateinit var eventMetadataService: EventMetadataService

    @Autowired
    private lateinit var segmentationService: SegmentService

    @GetMapping(value = ["/metadata"])
    fun getEventMetadta(): List<EventMetadata> {
        return eventMetadataService.getEventMetadata()
    }

    @GetMapping(value = ["/commonproperties"])
    fun getCommonProperties(): List<CommonMetadata> {
        return eventMetadataService.getCommonProperties()
    }

    @PostMapping(value = ["/save"])
    fun save(segment: Segment): ResponseEntity<Segment> {
        val persistedSegment = segmentationService.createSegment(segment)
        return ResponseEntity( persistedSegment, HttpStatus.CREATED)
    }


}