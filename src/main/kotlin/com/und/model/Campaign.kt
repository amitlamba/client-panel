package com.und.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "campaign")
open class Campaign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "campaign_type") //Email / SMS / Notifications etc
    @NotNull
    var campaignType: Short? = null

    @Column(name = "segmentation_id") //TODO Foreign Key
    @NotNull
    var segmentationID: Long? = null

    @Column(name = "frequency_type") //Repetitive or Onetime
    @NotNull
    var frequencyType: Short? = null

    @Column(name = "campaign_status") //TODO enum EmailDeliveryStatus or what?
    @NotNull
    var campaignStatus: Short? = null

    fun setCampaignType(campaignType: CampaignType) {
        this.campaignType = campaignType.value
    }

    fun getCampaignType(): CampaignType {
        return CampaignType.fromValue(this.campaignType!!)!!
    }

    fun setFrequencyType(frequencyType: FrequencyType) {
        this.frequencyType = frequencyType.value
    }

    fun getFrequencyType(): FrequencyType {
        return FrequencyType.fromValue(this.frequencyType!!)!!
    }
}