package com.und.web.controller

import com.und.common.utils.loggerFor
import com.und.security.utils.AuthenticationUtils
import com.und.service.CampaignService
import com.und.web.model.Campaign
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@CrossOrigin
@RestController
@RequestMapping("/campaign")
class CampaignController {

    companion object {

        protected val logger = loggerFor(CampaignController::class.java)
    }


    @Autowired
    lateinit var campaignService: CampaignService

    @GetMapping(value = ["list/all"])
    fun getCampaigns(@RequestParam(value = "id", required = false) id: Long? = null): List<Campaign> {
        return campaignService.getCampaigns()
    }


    @PostMapping(value = ["/save"])
    fun saveCampaign(@Valid @RequestBody campaign: Campaign): ResponseEntity<Campaign> {
        logger.info("campaign save request inititated ${campaign.name}")
        val clientId = AuthenticationUtils.clientID
        if (clientId != null) {
            val persistedCampaign = campaignService.save(campaign)
            return ResponseEntity(persistedCampaign, HttpStatus.CREATED)
        }
        logger.info("campaign saved with name ${campaign.name}")
        return ResponseEntity(campaign,HttpStatus.EXPECTATION_FAILED)
    }

    @PatchMapping(value = ["/pause/{campaignId}"])
    fun pauseCampaign(@PathVariable campaignId: Long): ResponseEntity<*> {
        val clientId = AuthenticationUtils.clientID
        if (clientId != null) {
            val status = campaignService.pause(campaignId)
            return ResponseEntity(status, HttpStatus.OK)
        }

        return ResponseEntity(campaignId,HttpStatus.EXPECTATION_FAILED)
    }

    @PatchMapping(value = ["/resume/{campaignId}"])
    fun resumeCampaign(@PathVariable campaignId: Long): ResponseEntity<*> {
        val clientId = AuthenticationUtils.clientID
        if (clientId != null) {
            val status = campaignService.resume(campaignId)
            return ResponseEntity(status, HttpStatus.OK)
        }

        return ResponseEntity(campaignId,HttpStatus.EXPECTATION_FAILED)
    }



}