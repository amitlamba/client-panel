package com.und.service

import com.und.web.model.AccountSettings
import com.und.web.model.EmailAddress
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest
class UserSettingsServiceTest {

    val clientID = 5L
    val userID = 9L

    @Autowired
    private lateinit var userSettingsService: UserSettingsService

    @Test
    fun testSaveAccountSettings() {
        val accountSettings = AccountSettings(arrayOf("http://userndot.com"),"Asia/Kolkata")
        userSettingsService.saveAccountSettings(accountSettings = accountSettings, clientID = clientID, userID = userID)
    }

    @Test
    fun testgetAccountSettings() {
        val accountSettings = userSettingsService.getAccountSettings(clientID = clientID, userID = userID)
        println(accountSettings)
    }

    @Test
    fun testAddEmailAddress() {
        val emailAddress = EmailAddress("amit@userndot.com", "Amit Lamba")
        userSettingsService.addSenderEmailAddress(emailAddress = emailAddress, clientID = clientID)
    }


    @Test
    fun testRemoveEmailAddress() {
        val emailAddress = EmailAddress("amit@userndot.com", "Amit Lamba")
        userSettingsService.removeSenderEmailAddress(emailAddress = emailAddress, clientID = clientID)
    }
}