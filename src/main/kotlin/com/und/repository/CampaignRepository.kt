package com.und.repository

import com.und.model.Campaign
import com.und.model.EmailCampaign
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CampaignRepository : JpaRepository<Campaign, Long> {
}