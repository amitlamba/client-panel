package com.und.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
//import com.und.config.EventStream
import com.und.model.jpa.*
import com.und.repository.CampaignRepository
import com.und.security.utils.AuthenticationUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import com.und.web.model.Campaign as WebCampaign

@Service
class CampaignService {

    @Autowired
    private lateinit var campaignRepository: CampaignRepository

/*    @Autowired
    private lateinit var eventStream: EventStream*/

    fun getCampaigns(): List<WebCampaign> {
        val clientID = AuthenticationUtils.clientID
        var campaigns = listOf<Campaign>()
        if(clientID != null) {
            campaigns = campaignRepository.findByClientID(clientID)
        }
        val webCampaigns = mutableListOf<WebCampaign>()
        campaigns.forEach { webCampaigns.add(buildWebCampaign(it)) }
        return webCampaigns
    }

    fun getEmailCampaigns(clientID: Long, id: Long? = null): List<Campaign> {
        var campaigns: List<Campaign>
        if (id == null) {
            campaigns = campaignRepository.findByClientIDAndCampaignType(clientID, CampaignType.EMAIL)
        } else {
            campaigns = listOf(campaignRepository.findByIdAndClientIDAndCampaignType(id, clientID, CampaignType.EMAIL))
        }
        return campaigns
    }


    fun save(webCampaign: WebCampaign): WebCampaign {
        val campaign = buildCampaign(webCampaign)

        val persistedCampaign = campaignRepository.save(campaign)
        //FIXME remove it later
        processSchedule(persistedCampaign.name)

        webCampaign.id = persistedCampaign.id
        return buildWebCampaign(campaign)
    }

    fun buildCampaign(webCampaign: WebCampaign): Campaign {
        val campaign = Campaign()
        with(campaign) {
            this.id = webCampaign.id
            this.clientID = AuthenticationUtils.clientID
            name = webCampaign.name
            appuserID = AuthenticationUtils.principal.id
            campaignType = webCampaign.campaignType
            segmentationID = webCampaign.segmentationID

            schedule = GsonBuilder().create().toJson(webCampaign.schedule)
        }



        if (webCampaign.campaignType == CampaignType.EMAIL) {
            //FIXME check if same template is in usage
            val emailcampaign = EmailCampaign()
            emailcampaign.appuserId = campaign.appuserID
            emailcampaign.clientID = campaign.clientID
            emailcampaign.templateId = webCampaign.templateID
            campaign.emailCampaign = emailcampaign
        } else  if (webCampaign.campaignType == CampaignType.SMS) {
            //FIXME check if same template is in usage
            val smscampaign = SmsCampaign()
            smscampaign.appuserId = campaign.appuserID
            smscampaign.clientID = campaign.clientID
            smscampaign.templateId = webCampaign.templateID
            campaign.smsCampaign = smscampaign
        }
        return campaign
    }


    fun buildWebCampaign(campaign: Campaign): WebCampaign {
        val webCampaign = WebCampaign()
        with(webCampaign) {
            id = campaign.id
            name = campaign.name

            campaignType = campaign.campaignType
            segmentationID = campaign.segmentationID

            schedule = Gson().fromJson(campaign.schedule, Schedule::class.java)
        }

        if (campaign.emailCampaign != null) {
            val emailcampaign = campaign.emailCampaign
            webCampaign.templateID = emailcampaign?.templateId
            webCampaign.campaignType = CampaignType.EMAIL
        } else if (campaign.smsCampaign != null) {
            val smsCampaign = campaign.smsCampaign
            webCampaign.templateID = smsCampaign?.templateId
            webCampaign.campaignType = CampaignType.SMS
        }
        return webCampaign
    }

    fun pause(campaignId: Long): Long? {
        return null
    }


    fun resume(campaignId: Long): Long? {
        return null
    }


    //@SendTo("createschedule")
    fun processSchedule(campaign: String): String {
        return campaign
    }

    //@StreamListener("createschedule")
    fun processSchedule2(campaign: String) {
        println(campaign)
        //return campaign
    }

}