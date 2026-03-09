package com.calculator

import java.text.NumberFormat
import java.util.*

class Calculator(var exp: String, var isFractional: Boolean, var isNegative: Boolean, var operation: String, var num: String) {
    private val fRu: NumberFormat = NumberFormat.getInstance(Locale("ru"))
    private fun String.removeWhitespaces() = replace("\\s".toRegex(), "")
    private fun String.norm() = replace(",".toRegex(), ".")

    private fun addDigit(digit: Char): String {
        if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN" && !exp.contains('E')) {
            if (exp == "0") {
                exp = ""
            }
            exp += digit
        }
        return result()
    }

    fun addOne(): String { return addDigit('1') }
    fun addTwo(): String { return addDigit('2') }
    fun addThree(): String { return addDigit('3') }
    fun addFour(): String { return addDigit('4') }
    fun addFive(): String { return addDigit('5') }
    fun addSix(): String { return addDigit('6') }
    fun addSeven(): String { return addDigit('7') }
    fun addEight(): String { return addDigit('8') }
    fun addNine(): String { return addDigit('9') }
    fun addZero(): String {
        if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
            if (exp.isEmpty() || isFractional || exp[0] != '0') {
                exp += "0"
            }
        }
        return result()
    }

    private fun addOperation(oper: String): String {
        if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
            if (operation != "" && exp.isEmpty()) {
                operation = oper
            } else if (exp.isNotEmpty() && exp.last() != '.') {
                if (operation != "") {
                    return equal()
                }
                fillParams("", exp,
                    isNegative1 = false,
                    isFractional1 = false,
                    operation1 = oper
                )
            }
        }
        return result()
    }

    fun add(): String { return addOperation("+") }
    fun sub(): String { return addOperation("-") }
    fun multiply(): String { return addOperation("*") }
    fun divide(): String { return addOperation("÷") }
    fun negate(): String {
        if (exp != "NaN") {
            if (operation == "" && exp.isNotEmpty() && exp != "0") {
                exp = if (isNegative) {
                    exp.substring(1, exp.length)
                } else {
                    "-$exp"
                }
                isNegative = !isNegative
            }
        }
        return result()
    }

    fun point(): String {
        if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
            if (!isFractional && exp.isNotEmpty()) {
                isFractional = true
                exp += "."
            }
        }
        return result()
    }

    fun delete(): String {
        if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN" && !exp.contains('E')) {
            if (exp.isNotEmpty()) {
                if (exp.length == 2 && isNegative) {
                    exp = ""
                    isNegative = false
                } else {
                    if (exp.last() == '.') {
                        if (exp[1] != '0' || !isNegative) {
                            isFractional = false
                        } else {
                            exp += "0"
                        }
                    }
                    exp = exp.substring(0, exp.length - 1)
                }
            } else if (operation != "" && exp.isEmpty()) {
                fillParams(
                    num, "", num.toDouble() < 0,
                    num.contains('.'), ""
                )
            }
        }
        return result()
    }

    fun allDelete(): String {
        fillParams("", "", isNegative1 = false, isFractional1 = false, operation1 = "")
        return result()
    }

    fun equal(): String {
        if (exp != "Infinity" && exp != "-Infinity" && exp != "NaN") {
            if (operation != "" && exp.isNotEmpty() && exp.last() != '.') {
                val num1: Double = num.toDouble()
                val num2: Double = exp.toDouble()
                var res = ""
                if (operation == "÷") {
                    val tmp = num1 / num2
                    when (tmp.toString()) {
                        "NaN" -> fillParams("NaN", "",
                            isNegative1 = false,
                            isFractional1 = false,
                            operation1 = ""
                        )
                        "-Infinity" -> fillParams("-Infinity", "",
                            isNegative1 = true,
                            isFractional1 = false,
                            operation1 = ""
                        )
                        "Infinity" -> fillParams("Infinity", "",
                            isNegative1 = false,
                            isFractional1 = false,
                            operation1 = ""
                        )
                        else -> fillParams(
                            fRu.format(num1 / num2).removeWhitespaces().norm(),
                            "", tmp < 0, res.contains('.'), ""
                        )
                    }
                } else {
                    when (operation) {
                        "+" -> res = fRu.format(num1 + num2)
                        "-" -> res = fRu.format(num1 - num2)
                        "*" -> res = fRu.format(num1 * num2)
                    }
                    res = res.removeWhitespaces().norm()
                    fillParams(
                        res, "", res.toDouble() < 0.0,
                        res.contains('.'), ""
                    )
                }
                if (exp == "-0") {
                    exp = "0"
                    isNegative = false
                }
            }
        }
        return result()
    }

    private fun fillParams(
        exp1: String, num1: String, isNegative1: Boolean,
        isFractional1: Boolean, operation1: String
    ) {
        exp = exp1
        num = num1
        isNegative = isNegative1
        isFractional = isFractional1
        operation = operation1
    }

    fun result(): String {
        return num + operation + exp
    }
}