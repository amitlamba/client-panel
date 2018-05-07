package com.und.repository.mongo

import com.und.model.mongo.eventapi.Event
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class EventCustomRepositoryImpl : EventCustomRepository {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    override fun usersFromEvent(query: String, clientId: Long): List<String> {
        val query = Query()
        //query.
        //FIXME query is empty

        return emptyList()
    }
}