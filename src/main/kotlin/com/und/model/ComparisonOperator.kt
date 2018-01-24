package com.und.model

class ComparisonOperator {
    lateinit var operatorType: OperatorType
    lateinit var operant1: Any
    var operant2: Array<Any> = arrayOf<Any>()


    companion object {
        fun operate(operatorType: OperatorType, operant1: Int, operant2: Array<Int> = arrayOf<Int>()): Boolean {
            when (operatorType) {
                OperatorType.greaterThan -> {
                    return operant1 > operant2.get(0)
                }
                OperatorType.lessThan -> {
                    return operant1 < operant2.get(0)
                }
                OperatorType.greaterThanEqualTo -> {
                    return operant1 >= operant2.get(0)
                }
                OperatorType.lessThanEqualTo -> {
                    return operant1 <= operant2.get(0)
                }
                OperatorType.notEqualTo -> {
                    return operant1 != operant2.get(0)
                }
                OperatorType.between -> {
                    return operant1 >= operant2.get(0) && operant1 <= operant2.get(1)
                }
                OperatorType.notBetween -> {
                    return operant1 < operant2.get(0) || operant1 > operant2.get(1)
                }
                OperatorType.containsAValue -> {
                    return operant2.contains(operant1)
                }
                OperatorType.doesNotContainAValue -> {
                    return !operant2.contains(operant1)
                }
            }
            return false
        }

        fun operate(operatorType: OperatorType, operant1: String, operant2: Array<String> = arrayOf<String>()): Boolean {
            when (operatorType) {
                OperatorType.greaterThan -> {
                    return operant1 > operant2.get(0)
                }
                OperatorType.lessThan -> {
                    return operant1 < operant2.get(0)
                }
                OperatorType.greaterThanEqualTo -> {
                    return operant1 >= operant2.get(0)
                }
                OperatorType.lessThanEqualTo -> {
                    return operant1 <= operant2.get(0)
                }
                OperatorType.notEqualTo -> {
                    return operant1 != operant2.get(0)
                }
                OperatorType.between -> {
                    return operant1 >= operant2.get(0) && operant1 <= operant2.get(1)
                }
                OperatorType.notBetween -> {
                    return operant1 < operant2.get(0) || operant1 > operant2.get(1)
                }
                OperatorType.containsAValue -> {
                    return operant2.contains(operant1)
                }
                OperatorType.doesNotContainAValue -> {
                    return !operant2.contains(operant1)
                }
            }
            return false
        }
    }
}

enum class OperatorType {
    greaterThan,
    lessThan,
    greaterThanEqualTo,
    lessThanEqualTo,
    notEqualTo,
    between,
    notBetween,
    containsAValue,
    doesNotContainAValue
}

class DateComparisonOperator {

}