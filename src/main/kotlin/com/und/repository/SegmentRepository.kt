package com.und.eventapi.repository

import com.und.model.Segment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SegmentRepository : JpaRepository<Segment, Long>




