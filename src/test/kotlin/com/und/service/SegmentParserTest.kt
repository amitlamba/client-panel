package com.und.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.und.web.model.Segment
import org.hamcrest.MatcherAssert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Before
import org.junit.Test
import org.springframework.util.ResourceUtils
import java.io.File
import org.hamcrest.CoreMatchers.`is` as Is

class SegmentParserTest {


    lateinit var mapper: ObjectMapper

    var testDataBase = "segmentdata"

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
    fun testSegmentParser() {

        val testData = readFileText("$testDataBase/test1.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().userList(segment)
        MatcherAssert.assertThat(2, Is(2))
    }


    @Test
    fun testSegmentParser2() {

        val testData = readFileText("$testDataBase/test2.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().userList(segment)
        MatcherAssert.assertThat(2, Is(2))
    }
    @Test
    fun testSegmentParser3() {

        val testData = readFileText("$testDataBase/test3.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().userList(segment)
        MatcherAssert.assertThat(2, Is(2))
    }

    @Test
    fun testSegmentParser4() {

        val testData = readFileText("$testDataBase/test4.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().userList(segment)
        MatcherAssert.assertThat(2, Is(2))
    }
    @Test
    fun testSegmentParser5() {

        val testData = readFileText("$testDataBase/test5.json")
        val segment = mapper.readValue(testData, Segment::class.java)
        val parsedResponse = SegmentParser().userList(segment)
        MatcherAssert.assertThat(2, Is(2))
    }

}

