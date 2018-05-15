package com.und.repository

import com.und.model.CampaignStatus
import com.und.model.jpa.Campaign
import com.und.model.jpa.CampaignType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CampaignRepository : JpaRepository<Campaign, Long> {
    fun findByClientID(clientID: Long = 1): List<Campaign>
    fun findByIdAndClientID(id: Long, clientID: Long): Campaign
    fun findByClientIDAndCampaignType(clientID: Long, campaignType: CampaignType): List<Campaign>
    fun findByIdAndClientIDAndCampaignType(id: Long, clientID: Long, campaignType: CampaignType): Campaign


    @Modifying(clearAutomatically = true)
    @Query("UPDATE campaign c SET c.campaign_status = :status WHERE c.client_idd = :clientId and c.id=:campaignId")
    fun updateScheduleStatus(@Param("campaignId") campaignId: Long,
                             @Param("clientId") clientId: Long,
                             @Param("status") status: String)

    @Query("SELECT campaign_status from campaign c  WHERE c.client_idd = :clientId and c.id=:campaignId")
    fun retrievecheduleStatus(@Param("campaignId") campaignId: Long,
                              @Param("clientId") clientId: Long): String

}