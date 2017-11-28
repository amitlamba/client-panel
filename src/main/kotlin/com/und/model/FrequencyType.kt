package com.und.model

enum class FrequencyType(val value: Short) {
    ONCE(1),
    REPETITIVE(2);

    companion object {
        private val map = FrequencyType.values().associateBy(FrequencyType::value)
        fun fromValue(type: Short) = map[type]
    }
}