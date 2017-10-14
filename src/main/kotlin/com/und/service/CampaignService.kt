package com.und.service

import com.und.model.*
import com.und.repository.CampaignRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CampaignService {

    @Autowired
    lateinit private var campaignRepository: CampaignRepository

    fun getCampaigns(clientID: Long, id: Long?): List<Campaign> {
        return listOf() //TODO: Write definition
    }

    fun saveEmailCampaign(clientID: Long, campaign: Campaign) {
        campaignRepository.save(campaign)
    }

}