package com.und.service

import com.und.model.mongo.eventapi.EventUser
import com.und.web.model.*

class SegmentParser {

    fun userList(segment: Segment): List<EventUser> {

        //did
        val did = segment.didEvents
        val didq= did?.let{parseEvents(did)}

        //and not
        val didnot = segment.didNotEvents
        val didnotq =  didnot?.let{parseEvents(didnot)}

        //and
        //for same properties use in/or
        segment.globalFilters

        //and
        segment.geographyFilters

        println("$didq $didnotq")

        return emptyList()
    }


    private fun parseEvents(did: DidEvents): String =
            parseEvents(did.events, did.joinCondition.conditionType)


    fun parseEvents(events: List<Event>, conditionType: ConditionType): String {
        val condition = parseCondition(conditionType)
        return events.map {
            " where name==${it.name}  ${parsePropertyFilters(it)} ${parseDateFilter(it.dateFilter)} ${whereFilterParse(it.whereFilter)} "
        }.joinToString { condition }
    }

    fun parseCondition(conditionType: ConditionType) =
            when (conditionType) {
                ConditionType.AllOf -> "and"
                ConditionType.AnyOf -> "or"
            }

    fun parsePropertyFilters(event: Event): String =
            event.propertyFilters.map {
                val filterType = it.filterType
                return when (filterType) {
                    PropertyFilterType.eventproperty -> {
                        //event property are like amount etc that comes with event custom
                        //search in event attributes
                        //check for type of operator and make query accordingly
                        "attributes.${it.name} ${it.operator} ${it.values} "
                    }
                    PropertyFilterType.genericproperty -> {
                        // these are like firstTime, lastTime, time of day, day of week, day of month etc.
                        //query them using time of event, e.g. for lastTime? firstTime
                        //firstTime when a user has done event with this name and no more after(count should be one)
                        //day of week is when occurrence is at that day of week
                        "not implemented"

                    }
                    PropertyFilterType.UTM -> {
                        //UTM are like generic property only but have special behaviour
                        "not implemented"
                    }
                }
/*            val propertName = it.name
            val operator = it.operator
            val type = it.type
            val valueUnit = it.valueUnit
            val values = it.values
            val propertiesPart = " ${it.name} ${it.filterType} ${it.operator} ${it.type} ${it.valueUnit} ${it.values}"*/
            }.joinToString { " " }


    fun parseDateFilter(dateFilters: DateFilter): String {
        val dateFilterq = " and creationTime ${dateFilters.operator} ${dateFilters.values}"
        return dateFilterq
    }

    fun whereFilterParse(whereFilter: WhereFilter): String {
        val whereFilterq = when (whereFilter.whereFilterName) {
            WhereFilterName.Count -> {
                " count ${whereFilter.operator} ${whereFilter.values.first()}"
            }
            WhereFilterName.SumOfValuesOf -> {

                " sumof(${whereFilter.propertyName})  ${whereFilter.operator} ${whereFilter.values} "
            }
        }
        return whereFilterq
    }
}

