package com.und.campaign.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "email_campaign")
class EmailCampaign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "campaign_id")
    @NotNull
    var campaignID: Long? = null

    @Column(name = "email_template_id")
    @NotNull
    var emailTemplateID: Long? = null

}