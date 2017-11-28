package com.und.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "email_campaign")
class EmailCampaign {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "email_campaign_id_seq")
    @SequenceGenerator(name = "email_campaign_id_seq", sequenceName = "email_campaign_id_seq", allocationSize = 1)
    var emailCampaignId: Long? = null

    @Column(name = "client_id")
    @NotNull
    var emailCampaignClientID: Long? = null

    @Column(name = "appuser_id")
    @NotNull
    var appuserID: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_id")
    @NotNull
    lateinit var campaign: Campaign

    @Column(name = "email_template_id")
    @NotNull
    var emailTemplateID: Long? = null

}