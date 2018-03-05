package com.und.service

import com.und.model.*
import com.und.model.jpa.Campaign
import com.und.model.jpa.EmailCampaign
import com.und.model.jpa.EmailTemplate
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@Ignore
@RunWith(SpringRunner::class)
@SpringBootTest
class CampaignServiceTest {

    @Autowired
    lateinit private var campaignService: CampaignService

    @Autowired
    lateinit private var emailTemplateService: EmailTemplateService

    @Test
    fun testCreateDummyCampaign() {
        var campaign = createDummyCampaign()
        campaignService.saveCampaign(campaign.clientID!!, campaign)
    }

    fun createDummyCampaign(): Campaign {
        var emailCampaign = EmailCampaign()
        var campaign = Campaign()
        campaign.clientID = 1
        campaign.appuserID = 1
        campaign.campaignType = CampaignType.EMAIL
        campaign.segmentationID = 1
        campaign.frequencyType = FrequencyType.ONCE
        campaign.schedule = System.currentTimeMillis().toString()
        campaign.campaignStatus = EmailDeliveryStatus.NOT_SCHEDULED
        emailCampaign.emailCampaignClientID = 1
        emailCampaign.appuserID = 1
        emailCampaign.emailTemplateID = 1
        campaign.emailCampaign = emailCampaign
//        emailCampaign.campaign=campaign
        return campaign
    }

    @Test
    fun testSaveEmailTemplate() {
        var emailTemplate = EmailTemplate()
        emailTemplate.clientID = 1
        emailTemplate.appuserID = 1
        emailTemplate.emailTemplateBody = "Dummy Body"
        emailTemplate.emailTemplateSubject = "Dummy Subject"
        emailTemplate.from = "amit@userndot.com"
        emailTemplate.messageType = MessageType.PROMOTIONAL
        emailTemplateService.saveEmailTemplate(emailTemplate)
    }
}