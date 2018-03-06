package com.und.web.controller

import com.und.model.jpa.Campaign
import com.und.security.utils.AuthenticationUtils
import com.und.service.CampaignService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/campaign")
class CampaignController {

    @Autowired
    lateinit var campaignService: CampaignService

    @GetMapping(value = ["list/"])
    fun getCampaigns(@RequestParam(value = "id", required = false) id: Long? = null): List<Campaign> {
        return campaignService.getCampaigns(AuthenticationUtils.clientID!!, id)
    }

    @GetMapping(value = ["/email/list"])
    fun getEmailCampaigns(@RequestParam(value = "id", required = false) id: Long? = null): List<Campaign> {
        return campaignService.getEmailCampaigns(AuthenticationUtils.clientID!!, id)
    }

    @PostMapping(value = ["/save"])
    fun saveCampaign(@RequestBody campaign: Campaign): Long {
        //TODO - Complete
        return 0
    }
}