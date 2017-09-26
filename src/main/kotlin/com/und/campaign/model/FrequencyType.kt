package com.und.campaign.model

enum class FrequencyType(val value: Int) {
    ONCE(1),
    REPETITIVE(2);

    companion object {
        private val map = FrequencyType.values().associateBy(FrequencyType::value);
        fun fromInt(type: Int) = map[type];
    }
}