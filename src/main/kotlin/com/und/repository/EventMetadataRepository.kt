package com.und.eventapi.repository

import com.und.eventapi.model.Event
import com.und.eventapi.model.Identity
import com.und.model.mongo.EventMetadata
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface EventMetadataRepository : MongoRepository<EventMetadata, String> {


}



