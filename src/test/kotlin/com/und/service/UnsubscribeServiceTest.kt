package com.und.service

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

//@Ignore
@RunWith(SpringRunner::class)
@SpringBootTest
class UnsubscribeServiceTest {

    @Autowired
    lateinit var unsubscribeService: UnsubscribeService

    @Test
    fun testUnsubscribeLinks() {
        val dummyUnsubscribeLinkParams = createDummyUnsubscribeLinkParams()
        println(dummyUnsubscribeLinkParams)
        val unsubscribeLink = unsubscribeService.createUnsubscribeLink(unsubscribeLinkParams = dummyUnsubscribeLinkParams)
        println(unsubscribeLink)
        val dataFromUnsubscribeLink = unsubscribeService.getDataFromUnsubscribeLink(unsubscribeLink = unsubscribeLink)
        println(dataFromUnsubscribeLink)
        assert(dummyUnsubscribeLinkParams.equals(dataFromUnsubscribeLink), {"The object could not be recreated"})
    }

    private fun createDummyUnsubscribeLinkParams(): UnsubscribeService.UnsubscribeLinkParams {
        return UnsubscribeService.UnsubscribeLinkParams("amit@userndot.com", clientID = 1, userID = "4104")
    }
}