package com.und.campaign.model

enum class CampaignType(val value: Short) {
    EMAIL(1),
    SMS(2),
    MOBILE_PUSH_NOTIFICATION(3);

    companion object {
        private val map = CampaignType.values().associateBy(CampaignType::value)
        fun fromValue(type: Short) = map[type]
    }
}