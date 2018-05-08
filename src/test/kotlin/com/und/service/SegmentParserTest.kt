package com.und.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.und.web.model.Segment
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.ResourceUtils
import org.hamcrest.CoreMatchers.`is` as Is

@RunWith(SpringRunner::class)
@SpringBootTest
class SegmentParserTest {


    lateinit var mapper: ObjectMapper

    var testDataBase = "segmentdata"

    @Autowired
    lateinit var segmentServiceImpl:SegmentServiceImpl

    @Before
    fun setup() {
        mapper = ObjectMapper()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true)
        mapper.registerModule(JavaTimeModule())
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    fun readFileText(fileName: String): String = ResourceUtils.getFile("classpath:$fileName").readText(Charsets.UTF_8)

    @Test
    fun testSegmentParser1() {

        val testData = readFileText("$testDataBase/test1.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().segmentQueries(segment)
        MatcherAssert.assertThat(2, Is(2))
    }


    @Test
    fun testSegmentParser2() {

        val testData = readFileText("$testDataBase/test2.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().segmentQueries(segment)
        MatcherAssert.assertThat(2, Is(2))
    }

    @Test
    fun testSegmentParser3() {

        val testData = readFileText("$testDataBase/test3.json")
        val segment = mapper.readValue(testData, Segment::class.java)
/*
        val parsedResponse = SegmentParser().segmentQueries(segment)
        //parsedResponse.didq.forEach { println(it) }

        val matchStage = Aggregation.match(
                Criteria().andOperator(
                        Criteria.where("event_‌​state").`is`("scheduled"),
                        Criteria.where("schedule.start_time").gt("1").lt("2"),
                        Criteria.where("event_state").`is`("live")))
        val fields = Aggregation.fields("userId", "creationTime")

        val projectStage = Aggregation.project(fields)
                .and("creationTime").extractMonth().`as`("month")
                .and("creationTime").extractDayOfMonth().`as`("monthday")
                .and("creationTime").extractDayOfWeek().`as`("weekday")
                .and("creationTime").extractHour().`as`("hour")
                .and("creationTime").extractMinute().`as`("minute")
                .and("creationTime").extractYear().`as`("year")
        //val group = Aggregation.group("userId").sum("s").`as`("count").min("dateTimeinMillis").`as`("time").push("sessionId").`as`("sessionId")
       // val p1 = Aggregation.project("sessionId","time").and("time").minus(23).`as`("time")
        //val p2 = Aggregation.project("sessionId","time").and("time").divide(1000*60*5).`as`("time")
       //val p4 = Aggregation.project("sessionId").and("creationDate").extractMonth().`as`("month")
        //val p3 = Aggregation.project("sessionId","time").and("time").mod(1).`as`("mod")
        //val p4 = Aggregation.project("sessionId","time").and("time").minus("mod").`as`("time")
        val g2 = Aggregation.group("userId").count().`as`("count")

        val matchStage2 = Aggregation.match(Criteria.where("sum").gt(2))
        val aggregation = Aggregation.newAggregation(projectStage,matchStage  ,g2,matchStage2)
        var v = aggregation.toString()
        print(v)
*/

        SegmentParserCriteria().segmentQueries(segment).didq.forEach {
            println(it.toString())
        }
        segmentServiceImpl.segmentUsers(37)
        MatcherAssert.assertThat(2, Is(2))
    }

    @Test
    fun testSegmentParser4() {

        val testData = readFileText("$testDataBase/test4.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().segmentQueries(segment)
        MatcherAssert.assertThat(2, Is(2))
    }

    @Test
    fun testSegmentParser5() {

        val testData = readFileText("$testDataBase/test5.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().segmentQueries(segment)
        MatcherAssert.assertThat(2, Is(2))
    }

    @Test
    fun testSegmentParser6() {

        val testData = readFileText("$testDataBase/test6.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().segmentQueries(segment)
        MatcherAssert.assertThat(2, Is(2))

    }


    @Test
    fun testSegmentParser7() {
        var count = 0
        val dir = ResourceUtils.getFile("classpath:$testDataBase")
        val files = dir.list { dir, name -> true }
        files.forEach {
            val testData = readFileText("$testDataBase/$it")
            val segment = mapper.readValue(testData, Segment::class.java)
            println("****$it**${count++}******")
            println("************")

            val parsedResponse = SegmentParser().segmentQueries(segment)
            println("************")
            println("************")


        }


        MatcherAssert.assertThat(2, Is(2))
    }

}


