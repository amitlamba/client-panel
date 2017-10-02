package com.und.campaign.model

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 * CREATE TABLE campign_trigger
(
id             BIGSERIAL NOT NULL CONSTRAINT campaign_trigger_pkey PRIMARY KEY,
client_id      BIGINT    NOT NULL REFERENCES appuser,
campaign_id    BIGINT    NOT NULL REFERENCES campaign,
trigger_time   TIMESTAMP NOT NULL,
trigger_status SMALLINT  NOT NULL
);
 */

@Entity
@Table(name = "campaign_trigger")
class CampaignTrigger {

    @Id
    @Column(name = "id")
    var id: Long? = null

    @Column(name = "client_id")
    @NotNull
    var clientID: Long? = null

    @Column(name = "campaign_id")
    @NotNull
    var campaignID: Long? = null

    @Column(name = "trigger_time")
    @NotNull
    var triggerTime: LocalDate? = null
}