package com.und.repository

import com.und.model.Campaign
import com.und.model.CampaignType
import com.und.model.EmailCampaign
import com.und.model.EmailTemplate
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CampaignRepository : JpaRepository<Campaign, Long> {
    fun findByClientID(clientID: Long = 1): List<Campaign>
    fun findByIdAndClientID(id: Long, clientID: Long): Campaign
    fun findByClientIDAndCampaignType(clientID: Long, campaignType: CampaignType): List<Campaign>
    fun findByIdAndClientIDAndCampaignType(id: Long, clientID: Long, campaignType: CampaignType): Campaign
}