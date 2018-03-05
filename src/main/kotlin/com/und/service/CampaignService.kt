package com.und.service

import com.und.model.*
import com.und.model.jpa.Campaign
import com.und.repository.CampaignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CampaignService {

    @Autowired
    lateinit private var campaignRepository: CampaignRepository

    fun getCampaigns(clientID: Long, id: Long? = null): List<Campaign> {
        var campaigns: List<Campaign>
        if(id == null){
            campaigns = campaignRepository.findByClientID(clientID)
        } else {
            campaigns = listOf(campaignRepository.findByIdAndClientID(id, clientID))
        }
        return campaigns
    }

    fun getEmailCampaigns(clientID: Long, id: Long? = null): List<Campaign> {
        var campaigns: List<Campaign>
        if(id == null){
            campaigns = campaignRepository.findByClientIDAndCampaignType(clientID, CampaignType.EMAIL)
        } else {
            campaigns = listOf(campaignRepository.findByIdAndClientIDAndCampaignType(id, clientID, CampaignType.EMAIL))
        }
        return campaigns
    }

    fun saveCampaign(clientID: Long, campaign: Campaign) {
        campaignRepository.save(campaign)
    }

}