package com.und.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "campaign")
open class Campaign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "campaign_id_seq")
    @SequenceGenerator(name = "campaign_id_seq", sequenceName = "campaign_id_seq", allocationSize = 1)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "appuser_id")
    @NotNull
    var appuserID: Long? = null

    @Column(name = "campaign_type") //Email / SMS / Notifications etc
    @NotNull
    @Enumerated(EnumType.STRING)
    var campaignType: CampaignType? = null

    @Column(name = "segmentation_id") //TODO Foreign Key
    @NotNull
    var segmentationID: Long? = null

    @Column(name = "frequency_type") //Repetitive or Onetime
    @NotNull
    @Enumerated(EnumType.STRING)
    var frequencyType: FrequencyType? = null

    @Column(name = "schedule")
    @NotNull
    var schedule: String? = null

    @Column(name = "campaign_status") //TODO enum EmailDeliveryStatus or what?
    @NotNull
    @Enumerated(EnumType.STRING)
    var campaignStatus: EmailDeliveryStatus? = null

    @OneToOne(mappedBy = "campaign",
            cascade = arrayOf(CascadeType.ALL),
            orphanRemoval = true)
    var emailCampaign: EmailCampaign = EmailCampaign()
        set(value) {
            field = value
            field.campaign = this
        }
}