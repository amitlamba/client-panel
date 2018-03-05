package com.und.service

import com.und.web.model.Segment as WebSegment
import org.springframework.stereotype.Service

@Service
interface SegmentService {

    fun createSegment(websegment: WebSegment): WebSegment

    fun allSegment(): List<WebSegment>
}

