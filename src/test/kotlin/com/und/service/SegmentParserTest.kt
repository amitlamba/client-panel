package com.und.service

import org.hamcrest.MatcherAssert
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Test
import org.hamcrest.CoreMatchers.`is` as Is

class SegmentParserTest {


    @Test
    public fun testSegmentParser() {
        MatcherAssert.assertThat(2, Is(2))
    }

}


class SampleSpec : Spek({
    given("some context") {
        on("executing some action") {
            it("should pass") {
                MatcherAssert.assertThat(2, Is(2))
            }
        }
    }

})