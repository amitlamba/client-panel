package com.und.campaign.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "campaign")
class Campaign {

    @Id
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "campaign_type") //Email / SMS / Notifications etc
    @NotNull
    var campaignType: Short? = null //TODO Could be a Enum if possible

    @Column(name = "segmentation_id") //TODO Foreign Key
    @NotNull
    var segmentationID: Long? = null

    @Column(name = "frequency_type") //Repetitive or Onetime
    @NotNull
    var frequencyType: Short? = null//TODO enum

    @Column(name = "campaign_status") //TODO enum
    @NotNull
    var campaignStatus: Short? = null
}