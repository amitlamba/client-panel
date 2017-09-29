package com.und.campaign.model

enum class EmailDeliveryStatus(val value: Int) {
    NOT_SCHEDULED(1),
    SCHEDULED(2),
    IN_PROCESS(3),
    DELIVERED(4);

    companion object {
        private val map = EmailDeliveryStatus.values().associateBy(EmailDeliveryStatus::value);
        fun fromInt(type: Int) = map[type]
    }
}