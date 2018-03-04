package com.und.service

import com.und.model.Segment
import com.und.model.api.Segment as WebSegment
import org.springframework.stereotype.Service

@Service
interface SegmentService {

    fun createSegment(websegment: WebSegment): WebSegment
}

