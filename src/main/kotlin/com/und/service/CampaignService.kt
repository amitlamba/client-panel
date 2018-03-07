package com.und.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.und.model.jpa.Campaign
import com.und.model.jpa.CampaignType
import com.und.model.jpa.EmailCampaign
import com.und.model.jpa.Schedule
import com.und.repository.CampaignRepository
import com.und.security.utils.AuthenticationUtils
import com.und.web.model.Segment
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.und.web.model.Campaign as WebCampaign

@Service
class CampaignService {

    @Autowired
    private lateinit var campaignRepository: CampaignRepository

    fun getCampaigns(clientID: Long, id: Long? = null): List<Campaign> {
        var campaigns: List<Campaign>
        if (id == null) {
            campaigns = campaignRepository.findByClientID(clientID)
        } else {
            campaigns = listOf(campaignRepository.findByIdAndClientID(id, clientID))
        }
        return campaigns
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

        campaignRepository.save(campaign)
        return webCampaign
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
        }
        return webCampaign
    }

}