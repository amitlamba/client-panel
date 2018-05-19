package com.und.service

import com.nhaarman.mockito_kotlin.whenever
import com.und.model.jpa.ClientSettings
import com.und.repository.ClientSettingsRepository
import com.und.repository.ServiceProviderCredentialsRepository
import com.und.web.model.AccountSettings
import com.und.web.model.EmailAddress
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.test.util.ReflectionTestUtils

//FIXME these tests are tesing nothing
class UserSettingsServiceTest {

    val clientID = 5L
    val userID = 9L

    @InjectMocks
    private lateinit var userSettingsService: UserSettingsService

    @Mock
    private lateinit var serviceProviderCredentialsRepository: ServiceProviderCredentialsRepository

    @Mock
    private lateinit var clientSettingsRepository: ClientSettingsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        ReflectionTestUtils.setField(userSettingsService, "clientSettingsRepository", clientSettingsRepository) // one hour
        ReflectionTestUtils.setField(userSettingsService, "serviceProviderCredentialsRepository", serviceProviderCredentialsRepository) // one hour
        val clientSetting = ClientSettings()
        clientSetting.authorizedUrls = "['http://userndot.com']"
        whenever(clientSettingsRepository.findByClientID(clientID)).thenReturn(clientSetting)

    }

    @Test
    fun testSaveAccountSettings() {
        val accountSettings = AccountSettings(urls = arrayOf("http://userndot.com"), timezone = "Asia/Kolkata")
        userSettingsService.saveAccountSettings(accountSettings = accountSettings, clientID = clientID, userID = userID)
    }

    @Test
    fun testgetAccountSettings() {
        val accountSettings = userSettingsService.getAccountSettings(clientID = clientID)
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