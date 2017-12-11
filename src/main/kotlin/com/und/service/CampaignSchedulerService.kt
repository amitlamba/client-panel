package com.und.service

import com.und.common.utils.loggerFor
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@EnableScheduling
class CampaignSchedulerService {

    companion object {
        protected val logger = loggerFor(CampaignSchedulerService::class.java)
    }

    @Scheduled(cron = "0 * * * * *")
    fun scheduleCampaignsInTrigger() {
        logger.debug("Running Scheduler cron = \"0 * * * * *\"")
        //TODO: Complete Scheduling process
    }
}