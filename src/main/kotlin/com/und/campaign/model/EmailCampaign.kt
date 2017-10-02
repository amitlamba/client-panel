package com.und.campaign.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "email_campaign")
class EmailCampaign {

    @Id
    @Column(name = "id")
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