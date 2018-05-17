package com.und.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.und.common.utils.loggerFor
import com.und.config.EventStream
import com.und.model.JobActionStatus
import com.und.model.JobDescriptor
import com.und.model.TriggerDescriptor
import com.und.model.jpa.*
import com.und.repository.CampaignRepository
import com.und.security.utils.AuthenticationUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.awt.event.ActionEvent
import com.und.web.model.Campaign as WebCampaign


@Service

class CampaignService {


    companion object {

        protected val logger = loggerFor(CampaignService::class.java)
    }

    @Autowired
    private lateinit var campaignRepository: CampaignRepository

    @Autowired
    private lateinit var eventStream: EventStream

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    fun getCampaigns(): List<WebCampaign> {
        val campaigns = AuthenticationUtils.clientID?.let { campaignRepository.findByClientID(it) }

        return campaigns?.map { buildWebCampaign(it) } ?: listOf()
    }


    fun save(webCampaign: WebCampaign): WebCampaign {
        val persistedCampaign = saveCampaign(webCampaign)
        return if(persistedCampaign!=null) buildWebCampaign(persistedCampaign) else  WebCampaign()
    }

    @Transactional
    private fun saveCampaign(webCampaign: com.und.web.model.Campaign): Campaign? {
        val campaign = buildCampaign(webCampaign)

        val persistedCampaign = campaignRepository.save(campaign)

        webCampaign.id = persistedCampaign.id
        logger.info("sending request to scheduler ${campaign.name}")
        val jobDescriptor = buildJobDescriptor(webCampaign, JobDescriptor.Action.CREATE)
        sendToKafka(jobDescriptor)
        return persistedCampaign
    }

    private fun buildJobDescriptor(campaign: WebCampaign, action: JobDescriptor.Action): JobDescriptor {

        fun buildTriggerDescriptor(): TriggerDescriptor {
            val triggerDescriptor = TriggerDescriptor()
            with(triggerDescriptor) {
                val recurring = campaign.schedule?.recurring
                val oneTime = campaign.schedule?.oneTime
                val multipleDates = campaign.schedule?.multipleDates
                when {
                    recurring != null -> {
                        cron = recurring.cronExpression
                        endDate = recurring.scheduleEnd?.endsOn
                        startDate = recurring.scheduleStartDate
                        countTimes = recurring.scheduleEnd?.occurrences ?: 0

                    }
                    oneTime != null -> {

                        fireTime = oneTime.campaignDateTime?.toLocalDateTime()
                    }
                    multipleDates != null -> {
                        fireTimes = multipleDates.campaignDateTimeList.map { it.toLocalDateTime() }
                    }
                }
            }

            return triggerDescriptor
        }

        val jobDescriptor = JobDescriptor()
        jobDescriptor.campaignName = campaign.name
        jobDescriptor.clientId = AuthenticationUtils.clientID.toString()
        jobDescriptor.campaignId = campaign.id.toString()
        jobDescriptor.action = action

        val triggerDescriptors = arrayListOf<TriggerDescriptor>()

        triggerDescriptors.add(buildTriggerDescriptor())
        jobDescriptor.triggerDescriptors = triggerDescriptors
        return jobDescriptor
    }

    fun buildCampaign(webCampaign: WebCampaign): Campaign {
        val campaign = Campaign()
        with(campaign) {
            this.id = webCampaign.id
            this.clientID = AuthenticationUtils.clientID
            name = webCampaign.name
            appuserID = AuthenticationUtils.principal.id
            campaignType = webCampaign.campaignType
            segmentationID = webCampaign.segmentationID


            schedule = objectMapper.writeValueAsString(webCampaign.schedule)
        }

        when (webCampaign.campaignType) {
            CampaignType.EMAIL -> {
                val emailcampaign = EmailCampaign()
                emailcampaign.appuserId = campaign.appuserID
                emailcampaign.clientID = campaign.clientID
                emailcampaign.templateId = webCampaign.templateID
                campaign.emailCampaign = emailcampaign
            }
            CampaignType.SMS -> {
                val smscampaign = SmsCampaign()
                smscampaign.appuserId = campaign.appuserID
                smscampaign.clientID = campaign.clientID
                smscampaign.templateId = webCampaign.templateID
                campaign.smsCampaign = smscampaign
            }
        }

        return campaign
    }


    fun buildWebCampaign(campaign: Campaign): WebCampaign {
        val webCampaign = WebCampaign()
        with(webCampaign) {
            id = campaign.id
            name = campaign.name

            campaignType = campaign.campaignType
            segmentationID = campaign.segmentationID
            dateCreated = campaign.dateCreated
            dateModified = campaign.dateModified
            status = campaign.status



            schedule = objectMapper.readValue(campaign.schedule, Schedule::class.java)
        }

        if (campaign.emailCampaign != null) {
            val emailcampaign = campaign.emailCampaign
            webCampaign.templateID = emailcampaign?.templateId
            webCampaign.campaignType = CampaignType.EMAIL
        } else if (campaign.smsCampaign != null) {
            val smsCampaign = campaign.smsCampaign
            webCampaign.templateID = smsCampaign?.templateId
            webCampaign.campaignType = CampaignType.SMS
        }
        return webCampaign
    }

    fun pause(campaignId: Long): Long? {
        val campaign = campaignRepository.findById(campaignId)
        val jobDescriptor = JobDescriptor()
        jobDescriptor.clientId = AuthenticationUtils.clientID.toString()
        jobDescriptor.campaignId = campaignId.toString()
        jobDescriptor.action = JobDescriptor.Action.PAUSE
        jobDescriptor.campaignName = if (campaign.isPresent) campaign.get().name else ""//set because it cant be null FIXME find some other way around

        sendToKafka(jobDescriptor)
        return campaignId
    }


    fun resume(campaignId: Long): Long? {
        return pause(campaignId)
    }


    fun sendToKafka(jobDescriptor: JobDescriptor) = eventStream.scheduleJobSend().send(MessageBuilder.withPayload(jobDescriptor).build())


    @StreamListener("scheduleJobAckReceive")
    @Transactional
    fun schedulerAcknowledge(jobActionStatus: JobActionStatus) {
        val status = jobActionStatus.status
        val action = jobActionStatus.jobAction
        val clientId = action.clientId.toLong()
        val campaignId = action.campaignId.toLong()
        val campignName = action.campaignName
        val actionPerformed = action.action
        if (status == JobActionStatus.Status.OK) {
            //FIXME unite actionperformed and status action
            campaignRepository.updateScheduleStatus(campaignId, clientId, actionPerformed.name)
            //campaignRepository.updatestatus
        } else {
            if (actionPerformed == JobDescriptor.Action.CREATE && status == JobActionStatus.Status.ERROR) {
                //FIXME send emails warning alerts etc
                campaignRepository.updateScheduleStatus(campaignId, clientId, JobDescriptor.Action.FAILED.name)
                logger.error(" Campaign Schedule couldnt be created")
            } else if (status == JobActionStatus.Status.ERROR) {
                logger.error("  Schedule action couldn't be performed")
                //FIXME send errors
            }
        }

    }

}