package com.und.repository.mongo

import com.und.model.mongo.eventapi.EventUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.MatchOperation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.aggregation.AggregationResults
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation




class EventCustomRepositoryImpl : EventCustomRepository {

    @Autowired
    lateinit var mongoTemplate: MongoTemplate

    override fun usersFromEvent(query: String, clientId: Long): List<String> {
        val matchStage = Aggregation.match(Criteria("foo").`is`("bar"))
        val matchStage2 = Aggregation.match(Criteria("foo").`is`("bar"))
        val matchStage3 = Aggregation.match(Criteria("foo").`is`("bar"))
        val projectStage = Aggregation.project("foo", "bar.baz")

        val aggregation = Aggregation.newAggregation(matchStage, matchStage2, projectStage)

        val output = mongoTemplate.aggregate(aggregation, "foobar", EventUser::class.java)
        val query = Query()
        //AggregateOperation
        //query.
        //FIXME query is empty

        return emptyList()
    }
}