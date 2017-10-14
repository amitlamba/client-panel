package com.und.service

import com.und.model.Campaign
import org.springframework.stereotype.Service

@Service
class CampaignService {
    fun getCampaigns(clientID: Long, id: Long?): List<Campaign> {
        return listOf() //TODO: Write definition
    }
}