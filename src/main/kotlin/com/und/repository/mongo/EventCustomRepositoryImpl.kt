package com.und.repository.mongo

import com.und.model.mongo.eventapi.Event
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation


class EventCustomRepositoryImpl : EventCustomRepository {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    override fun usersFromEvent(query: Aggregation, clientId: Long): List<String> {


        val output = mongoTemplate.aggregate(query, "${clientId}_event", Event::class.java)
        //val query = Query()
        //AggregateOperation
        //query.
        //FIXME query is empty
        val results = output.uniqueMappedResult
        println(results)

        return emptyList()
    }
}