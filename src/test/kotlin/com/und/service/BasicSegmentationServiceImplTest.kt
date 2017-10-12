package com.und.service

import org.junit.Test

class BasicSegmentationServiceImplTest {

    @Test
    fun getSegmentationUsersTestWithDummy() {
        val basicSegmentationServiceImpl = BasicSegmentationServiceImpl()
        val dummyEventUser = basicSegmentationServiceImpl.getSegmentationUsers(1)
        println(dummyEventUser)
    }
}