package com.und.model

import com.und.eventapi.model.Event
import java.util.*

class Segmentation {
    lateinit var segmentName: String
    lateinit var userAccessTime: UserAccessTime
    lateinit var userLocations: UserLocations
    lateinit var userAttributes: List<UserAttribute>
    lateinit var userFirstAcquisitionSource: List<String>
    lateinit var userIds: List<String>
    lateinit var userBehaviors: List<UserBehavior>
}

class UserBehavior {
    var andOr: AndOr? = null
    var didEvents: Boolean = true
    var events: List<Event> = listOf()
    var numberOfTimes: Int? = null
    var eventAttributes: List<EventAttributes> = listOf()

    class EventAttributes {
        var andOr: AndOr? = null
        lateinit var attributeName: String
        lateinit var comparisonOperator: ComparisonOperator
    }
}

class UserAttribute {
    var andOr: AndOr? = null
    lateinit var userAttributeName: String
    lateinit var comparisonOperator: ComparisonOperator
}

class UserLocations {
    var inCountries: Boolean = true
    lateinit var countries: List<Country>

    class Country {
        lateinit var countryName: String
        var countryID: Int? = null
    }
}

class UserAccessTime {

}

enum class AndOr {
    AND,
    OR
}

class Technology {
    var androidProperties: AndroidProperties? = null
    var iosProperties: IosProperties? = null
    var webProperties: WebProperties? = null

    class AndroidProperties {
        var appInstallationDate: Date? = null
        var lastSeen: Date? = null
        var totalTimeSpent: ComparisonOperator? = null
        var appVersionName: List<String>? = null
        var appId: List<String>? = null
        var appVersionCode: ComparisonOperator? = null
        var apiVersion: ComparisonOperator? = null
        var sdkVersion: ComparisonOperator? = null
        var manufacturer: List<String>? = null
        var model: List<String>? = null
        var brand: List<String>? = null
    }

    class IosProperties {
        var appInstallationDate: Date? = null
        var lastSeen: Date? = null
        var totalTimeSpent: ComparisonOperator? = null
        var appVersionName: List<String>? = null
        var appId: List<String>? = null
        var appVersionCode: ComparisonOperator? = null
        var apiVersion: ComparisonOperator? = null
        var sdkVersion: ComparisonOperator? = null
        var manufacturer: List<String>? = null
    }

    class WebProperties {
        var devices: List<String>? = null
        var operatingSystem: List<String>? = null
        var browser: List<String>? = null
    }
}