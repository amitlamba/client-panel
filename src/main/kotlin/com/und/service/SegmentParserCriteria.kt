package com.und.service

import com.und.web.model.*
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.GroupOperation
import org.springframework.data.mongodb.core.aggregation.MatchOperation
import org.springframework.data.mongodb.core.query.Criteria
import java.time.LocalDateTime

/*
1. parse events, didn't do and do
2. parse use properties
3. parse geography
4. check data type of property and use comparator's properly
5. use aggregate query for count, sum of and first time etc.
6. and in the end convert this to valid mongo query(one for events and other for user properties)
7. create proper mongo indexes
8. combine results of both
9. cache and store parsed query
 */
class SegmentParserCriteria {


    fun segmentQueries(segment: Segment): SegmentQuery {

        //did
        val did = segment.didEvents
        val didq =
             did?.let { Pair(parseEvents(it.events, false), it.joinCondition.conditionType)}?:Pair(emptyList(), ConditionType.AllOf)


        //and not
        val didnot = segment.didNotEvents
        val didnotq = didnot?.let { Pair(parseEvents(it.events, false), it.joinCondition.conditionType)}?:Pair(emptyList(), ConditionType.AnyOf)





        return SegmentQuery(didq, didnotq)
    }


    fun parseEvents(events: List<Event>, did: Boolean): List<Aggregation> {

        return events.map {
            val whereCond = if (did) it.whereFilter?.let { whereFilterParse(it) } else null
            val matches = mutableListOf<Criteria>()
            matches.addAll(parsePropertyFilters(it))
            matches.plus(Criteria.where("name").`is`(it.name))
            matches.plus(parseDateFilter(it.dateFilter))
            var fields = Aggregation.fields("userId", "creationTime", "clientId")
            matches.forEach { criteria ->
                val name = criteria.key
                if (name != null) {
                    fields = fields.and(name, name)
                }
            }
            val project = Aggregation.project(fields)
                    .and("creationTime").extractMonth().`as`("month")
                    .and("creationTime").extractDayOfMonth().`as`("monthday")
                    .and("creationTime").extractDayOfWeek().`as`("weekday")
                    .and("creationTime").extractHour().`as`("hour")
                    .and("creationTime").extractMinute().`as`("minute")
                    .and("creationTime").extractYear().`as`("year")


            val matchOps = Aggregation.match(Criteria().andOperator(*matches.toTypedArray()))

            if (whereCond != null) {
                val group = whereCond.first
                val matchOnGroup = whereCond.second
                Aggregation.newAggregation(project, matchOps, group, matchOnGroup)
            } else {
                val group = Aggregation.group(Aggregation.fields().and("userId", "userId"))
                Aggregation.newAggregation(project, matchOps, group)
            }


        }

    }

    private fun parsePropertyFilters(event: Event): List<Criteria> {
        return event.propertyFilters.groupBy { it.name }.map {
            eventPropertyQuery(it.value)
        }.filter({ it -> true })

    }


    private fun eventPropertyQuery(eventProperties: List<PropertyFilter>): Criteria {
        fun parseProperty(propertyFilter: PropertyFilter): Criteria {
            return when (propertyFilter.filterType) {
                PropertyFilterType.eventproperty -> {

                    match(propertyFilter.values, propertyFilter.operator, "attributes.${propertyFilter.name}", propertyFilter.type, propertyFilter.valueUnit)
                }
                PropertyFilterType.genericproperty -> {
                    val values = propertyFilter.values
                    when (propertyFilter.name) {
                        "Time of day" -> {
                            val (startHour, startMinute, endHour, endMinute) = values
                            Criteria().orOperator(
                                    Criteria("hour").gte(startHour).lte(endHour),
                                    Criteria().andOperator(Criteria("hour").`is`(startHour), Criteria("minute").gt(startMinute)),
                                    Criteria().andOperator(Criteria("hour").`is`(endHour), Criteria("minute").lt(endMinute))
                            )


                        }
                        "First Time" -> {
                            //FIXME how to do this?
                            "{count of event == 1}"
                            Criteria()
                        }
                        "Day of Week" -> {
                            Criteria.where("weekday").`in`(values)
                        }
                        "Day Of Month" -> {
                            Criteria.where("month").`in`(values)
                        }
                        else -> throw Exception("{Invalid generic Property ${propertyFilter.name}}")
                    }


                }
                PropertyFilterType.UTM -> {
                    //FIXME UTM are like generic property only but have special behaviour
                    when (propertyFilter.name) {
                        "UTM Source" -> Criteria()//listOf("attributes.${propertyFilter.name}", propertyFilter.operator, propertyFilter.values).joinToString(space)
                        "UTM Visited" -> Criteria()//listOf("attributes.${propertyFilter.name}", propertyFilter.operator, propertyFilter.values).joinToString(space)
                        else -> throw Exception("Invalid UTM Property ${propertyFilter.name}")
                    }
                }
                else -> {
                    throw Exception("type of filter can be eventptoperty, genericproperty or UTM but is null")
                }
            }
        }

        val rs = eventProperties.map {
            parseProperty(it)
        }
        val finalq = if (rs.size > 1) {

            val x = rs.toTypedArray()
            Criteria().orOperator(*x)


        } else rs.first()
        //joined with or for same name properties of same event
        return finalq
    }

    private fun parseDateFilter(dateFilters: DateFilter): Criteria {

        return match(dateFilters.values, dateFilters.operator.name, "creationTime", DataType.date, dateFilters.valueUnit)

    }


    private fun whereFilterParse(whereFilter: WhereFilter): Pair<GroupOperation, MatchOperation> {
        val values = whereFilter.values
        return if (values != null) {
            return when (whereFilter.whereFilterName) {
                WhereFilterName.Count -> {

                    val group = Aggregation.group(Aggregation.fields().and("userId", "userId")).count().`as`("count")
                    val match = matchNumber(values.map { it.toString() }, whereFilter.operator?.name
                            ?: "Equals", "count")
                    Pair(group, Aggregation.match(match))
                }
                WhereFilterName.SumOfValuesOf -> {

                    val group = Aggregation.group(Aggregation.fields().and("userId", "userId")).sum(whereFilter.propertyName).`as`("sumof")
                    val match = matchNumber(values.map { it.toString() }, whereFilter.operator?.name
                            ?: "Equals", "sumof")

                    Pair(group, Aggregation.match(match))
                }
                else -> throw Exception("invalid aggregate expression can only be count or sum  but is ${whereFilter.whereFilterName}")
            }
        } else throw Exception("no value for groupin criteria")


    }


    private fun match(values: List<String>, operator: String, fieldName: String, type: DataType, unit: String): Criteria {
        return when (type) {
            DataType.string -> matchString(values = values, operator = operator, fieldName = fieldName)
            DataType.number -> matchNumber(valuesString = values, operator = operator, fieldName = fieldName)
            DataType.date -> matchDate(values = values, operator = operator, fieldName = fieldName, unit = unit)
            else -> Criteria()

        }

    }

    private fun matchString(values: List<String>, operator: String, fieldName: String): Criteria {
        return when (operator) {
            "Equals" -> {
                Criteria.where(fieldName).`is`(values.first())
            }

            "NotEquals" -> {
                Criteria.where(fieldName).ne(values.first())
            }
            "Contains" -> {
                Criteria.where(fieldName).`in`(values)
            }
            "DoesNotContain" -> {
                Criteria.where(fieldName).nin(values)
            }

            "Exits" -> {
                Criteria.where(fieldName).exists(true)
            }
            "DoesNotExist" -> {
                Criteria.where(fieldName).exists(false)
            }

            else -> Criteria()
        }

    }


    private fun matchNumber(valuesString: List<String>, operator: String, fieldName: String): Criteria {
        val values = valuesString.map { it.toLong() }
        return when (operator) {
            "Equals" -> {
                Criteria.where(fieldName).`is`(values.first())
            }
            "Between" -> {
                Criteria.where(fieldName).gt(values.first()).lt(values.last())
            }
            "GreaterThan" -> {
                Criteria.where(fieldName).gt(values.first())
            }
            "LessThan" -> {
                Criteria.where(fieldName).gt(values.first())
            }
            "NotEquals" -> {
                Criteria.where(fieldName).ne(values.first())
            }

            "Exits" -> {
                Criteria.where(fieldName).exists(true)
            }
            "DoesNotExist" -> {
                Criteria.where(fieldName).exists(false)
            }
            else -> Criteria()
        }

    }

    private fun matchDate(values: List<String>, operator: String, unit: String, fieldName: String): Criteria {

        return when (operator) {

            "Before" -> {
                val date = LocalDateTime.parse(values.first())
                Criteria.where(fieldName).lt(date)
            }
            "After" -> {
                val date = LocalDateTime.parse(values.first())
                Criteria.where(fieldName).gt(date)
            }
            "On" -> {
                val date = LocalDateTime.parse(values.first())
                Criteria.where(fieldName).`is`(date)
            }
            "Between" -> {

                val startDate = LocalDateTime.parse(values.first())
                val endDate = LocalDateTime.parse(values.last())
                Criteria.where(fieldName).lte(startDate).gte(endDate)
            }
            "InThePast" -> {

                val startDate = minus(LocalDateTime.now(), unit, values.first().toLong())
                val endDate = minus(LocalDateTime.now(), unit, values.last().toLong())

                Criteria.where(fieldName).lte(startDate).gte(endDate)

            }
            "WasExactly" -> {
                val date = LocalDateTime.now().minusDays(values.first().toLong())
                Criteria.where(fieldName).`is`(date)
            }
            "Today" -> {
                Criteria.where(fieldName).`is`(LocalDateTime.now())
            }
            "InTheFuture" -> {

                val startDate = plus(LocalDateTime.now(), unit, values.first().toLong())
                val endDate = plus(LocalDateTime.now(), unit, values.last().toLong())

                Criteria.where(fieldName).lte(startDate).gte(endDate)
            }
            "WillBeExactly" -> {
                val date = LocalDateTime.now().plusDays(values.first().toLong())
                Criteria.where(fieldName).`is`(date)
            }
            "Exits" -> {
                Criteria.where(fieldName).exists(true)
            }
            "DoesNotExist" -> {
                Criteria.where(fieldName).exists(false)
            }
            else -> Criteria()


        }

    }

    private fun plus(date: LocalDateTime, unit: String, value: Long): LocalDateTime {
        return when (unit) {
            "day" -> date.minusDays(value)
            "week" -> date.minusWeeks(value)
            "month" -> date.minusMonths(value)
            "year" -> date.minusYears(value)
            else -> date
        }
    }

    private fun minus(date: LocalDateTime, unit: String, value: Long): LocalDateTime {
        return when (unit) {
            "day" -> date.plusDays(value)
            "week" -> date.plusWeeks(value)
            "month" -> date.plusMonths(value)
            "year" -> date.plusYears(value)
            else -> date
        }
    }
}
/*
    private fun filterGlobalQOr(globalFilters: List<GlobalFilter>): String {


        fun parse(filter: Map<String, List<GlobalFilter>>): String {
            val q = filter.map {
                val glFilters = it.value
                "(" + glFilters.map { filter ->
                    val type = filter.type
                    val filterString = when (type) {
                        "string" -> "[" + filter.values.mapNotNull { it.toString() }.joinToString(",") + "]"
                        "number" -> "[" + filter.values.mapNotNull { it.toString() }.joinToString(",") + "]"
                        "date" -> "[" + filter.values.mapNotNull { it.toString() }.joinToString(",") + "]"
                        else -> "[" + filter.values.mapNotNull { it.toString() }.joinToString(",") + "]"
                    }
                    "${filter.globalFilterType}.${filter.name} ${filter.operator} $filterString"
                }.joinToString(or) + ")$newLine"

            }.joinToString(and)
            return q
        }

        val gFilters = globalFilters
                .groupBy { it.globalFilterType }

        return gFilters.map { it ->
            val filter = it.value.groupBy { it.name }
            parse(filter)
        }.joinToString(and)
    }
}
*/

class SegmentQuery(val didq: Pair<List<Aggregation>, ConditionType>, val didntq: Pair<List<Aggregation>, ConditionType>)