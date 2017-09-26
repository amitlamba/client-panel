package com.und.campaign.model

enum class EmailMessageType(val value: Int) {
    TRANSACTIONAL(1),
    PROMOTIONAL(2);

    companion object {
        private val map = EmailMessageType.values().associateBy(EmailMessageType::value);
        fun fromInt(type: Int) = map[type]
    }
}