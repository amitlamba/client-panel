package com.und.web.model

import java.time.LocalDateTime


class Segment {

    var id: Long? = null


    var clientID: Long? = null

    var name: String = ""
    var type: String = ""
    var creationDate: LocalDateTime = LocalDateTime.now()
    var conversionEvent: String = ""
    var didEvents: DidEvents = DidEvents()
    var didNotEvents: DidEvents = DidEvents()
    var globalFilters: Array<GlobalFilter> = arrayOf()
    var geographyFilters: Array<Geography> = arrayOf()
}

class DidEvents {
    var description: String = ""
    var joinCondition: JoinCondition = JoinCondition()
    var events: Array<Event> = arrayOf()
}

class JoinCondition {
    var anyOfCount: Long = 0
    var conditionType: String = "AllOf"// AllOf / AnyOf
}

class Event {
    var name: String = ""
    var dateFilter: DateFilter = DateFilter()
    var propertyFilters: Array<PropertyFilter> = arrayOf()
    var whereFilter: WhereFilter = WhereFilter()
}

class DateFilter {
    var operator: DateOperator = DateOperator.After
    var values: Array<String> = arrayOf()
    var valueUnit: String = ""
}

class PropertyFilter {
    var name: String = ""
    var type: PropertyType = PropertyType.String
    var filterType: PropertyFilterType = PropertyFilterType.UTM
    var operator: String = ""
    var values: Array<String> = arrayOf()
    var valueUnit: String = ""
}

enum class PropertyType {
     String ,
     number,
     date
}

enum class PropertyFilterType {
     eventproperty,
     genericproperty,
     UTM
}

class WhereFilter {
    var whereFilterName: WhereFilterName = WhereFilterName.Count
    var propertyName: String = ""
    var operator: NumberOperator = NumberOperator.Between
    var values: Array<Long> = arrayOf()
}

enum class WhereFilterName {
     Count,
     SumOfValuesOf
}

enum class DateOperator {
    Before,
    After,
    On,
    Between,
    InThePast,
    WasExactly,
    Today,
    InTheFuture,
    WillBeExactly
}

enum class NumberOperator {
    Equals ,
    Between,
    GreaterThan,
    LessThan,
    NotEquals,
    Exists,
    DoesNotExist
}

enum class StringOperator {
    Equals ,
    NotEquals,
    Contains,
    DoesNotContain ,
    Exists,
    DoesNotExist
}

class GlobalFilter {
    var globalFilterType: GlobalFilterType = GlobalFilterType.APPFIELDS
    var name: String = ""
    var type: String = ""
    var operator: String = ""
    var values: Array<Any> = arrayOf()
    var valueUnit: String = ""
}

enum class GlobalFilterType(val type: String) {
    USERPROPERTIES("UserProperties"),
    DEMOGRAPHICS("Demographics"),
    TECHNOGRAPHICS("Technographics"),
    REACHABILITY("Reachability"),
    APPFIELDS("appFields")
}

class Geography {
    var country: Country = Country()
    var state: State = State()
    var city: City = City()
}

class Country {
    var id: Long = 0
    var name: String = ""
}

class State {
    var id: Long = 0
    var name: String = ""
}

class City {
    var id: Long = 0
    var name: String = ""
}

class RegisteredEvent {
    var name: String = ""
    var properties: Array<RegisteredEventProperties> = arrayOf()
}

class RegisteredEventProperties {
    var name: String = ""
    var dataType: String = ""
    var regex: String = ""
    var options: Array<Any> = arrayOf()
}

class GlobalFilterItem {
    var value: String = ""
    var displayName: String = ""
    var type: String = ""
}
