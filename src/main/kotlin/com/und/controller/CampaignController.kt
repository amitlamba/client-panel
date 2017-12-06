package com.und.controller

import com.und.model.Campaign
import com.und.security.utils.AuthenticationUtils
import com.und.service.CampaignService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class CampaignController {

    @Autowired
    lateinit var campaignService: CampaignService

    @RequestMapping(value = "/client/get-campaigns", method = arrayOf(RequestMethod.GET))
    fun getCampaigns(@RequestParam(value = "id", required = false) id: Long? = null): List<Campaign> {
        return campaignService.getCampaigns(AuthenticationUtils.clientID!!, id)
    }

    @RequestMapping(value = "/client/get-email-campaigns", method = arrayOf(RequestMethod.GET))
    fun getEmailCampaigns(@RequestParam(value = "id", required = false) id: Long? = null): List<Campaign> {
        return campaignService.getEmailCampaigns(AuthenticationUtils.clientID!!, id)
    }

    fun saveCampaign(campaign: Campaign): Long {
        //TODO - Complete
        return 0
    }
}