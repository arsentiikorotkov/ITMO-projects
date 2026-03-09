package com.calculator

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private val calculator = Calculator(
        "-32.1",
        isFractional = true,
        isNegative = false,
        operation = "+",
        num = "6897"
    )

    private fun num32() {
        calculator.addThree()
        calculator.addTwo()
    }

    private fun inf(): String {
        num32()
        calculator.divide()
        calculator.addZero()
        return calculator.equal()
    }

    private fun nan(): String {
        calculator.addZero()
        calculator.divide()
        calculator.addZero()
        return calculator.equal()
    }

    private fun destruction(): String {
        num32()
        calculator.delete()
        calculator.sub()
        calculator.addTwo()
        return calculator.point()
    }

    @Before
    fun clean() {
        calculator.allDelete()
    }

    @Test
    fun resultTest() {
        calculator.addThree()
        calculator.addTwo()
        calculator.sub()
        calculator.addSix()
        calculator.addZero()
        val res = calculator.result()
        val expected = "32-60"
        assertEquals(expected, res)
    }

    @Test
    fun allDeleteTest() {
        val expectedResult = ""
        val expectedFractional = false
        val expectedNegative = false
        val resResult = calculator.result()
        val resFractional = calculator.isFractional
        val resNegative = calculator.isNegative
        assertEquals(expectedResult, resResult)
        assertEquals(expectedFractional, resFractional)
        assertEquals(expectedNegative, resNegative)
    }

    @Test
    fun addZeroTest() {
        val res = calculator.addZero()
        val expected = "0"
        assertEquals(expected, res)
    }

    @Test
    fun addDigitAfterZeroTest() {
        calculator.addZero()
        val res = calculator.addFour()
        val expected = "4"
        assertEquals(expected, res)
    }

    @Test
    fun addOneTest() {
        val res = calculator.addOne()
        val expected = "1"
        assertEquals(expected, res)
    }

    @Test
    fun addTwoTest() {
        val res = calculator.addTwo()
        val expected = "2"
        assertEquals(expected, res)
    }

    @Test
    fun addThreeTest() {
        val res = calculator.addThree()
        val expected = "3"
        assertEquals(expected, res)
    }

    @Test
    fun addFourTest() {
        val res = calculator.addFour()
        val expected = "4"
        assertEquals(expected, res)
    }

    @Test
    fun addFiveTest() {
        val res = calculator.addFive()
        val expected = "5"
        assertEquals(expected, res)
    }

    @Test
    fun addSixTest() {
        val res = calculator.addSix()
        val expected = "6"
        assertEquals(expected, res)
    }

    @Test
    fun addSevenTest() {
        val res = calculator.addSeven()
        val expected = "7"
        assertEquals(expected, res)
    }

    @Test
    fun addEightTest() {
        val res = calculator.addEight()
        val expected = "8"
        assertEquals(expected, res)
    }

    @Test
    fun addNineTest() {
        val res = calculator.addNine()
        val expected = "9"
        assertEquals(expected, res)
    }

    @Test
    fun addAddFullExampleTest() {
        calculator.addEight()
        calculator.addSeven()
        calculator.add()
        val res = calculator.addFour()
        val expected = "87+4"
        assertEquals(expected, res)
    }

    @Test
    fun addSubFullExampleTest() {
        calculator.addThree()
        calculator.addOne()
        calculator.addZero()
        calculator.sub()
        calculator.addSix()
        val res = calculator.addTwo()
        val expected = "310-62"
        assertEquals(expected, res)
    }

    @Test
    fun addMultiplyFullExampleTest() {
        calculator.addEight()
        calculator.multiply()
        val res = calculator.addZero()
        val expected = "8*0"
        assertEquals(expected, res)
    }

    @Test
    fun divideFullExampleTest() {
        calculator.addNine()
        calculator.addOne()
        calculator.divide()
        calculator.addNine()
        val res = calculator.addOne()
        val expected = "91÷91"
        assertEquals(expected, res)
    }

    @Test
    fun operationAfterEmptyTest() {
        val res = calculator.multiply()
        val expected = ""
        assertEquals(expected, res)
    }

    @Test
    fun operationAfterOperationTest() {
        calculator.addTwo()
        calculator.sub()
        val res = calculator.divide()
        val expected = "2÷"
        assertEquals(expected, res)
    }

    @Test
    fun pointTest() {
        num32()
        calculator.point()
        val resResult = calculator.addFour()
        val expectedResult = "32.4"
        val resFractional = calculator.isFractional
        val exceptedFractional = true
        assertEquals(expectedResult, resResult)
        assertEquals(exceptedFractional, resFractional)
    }

    @Test
    fun pointAfterEmptyTest() {
        val res = calculator.point()
        val expected = ""
        assertEquals(expected, res)
    }

    @Test
    fun pointAfterPointTest() {
        num32()
        calculator.point()
        calculator.addFour()
        val res = calculator.point()
        val expected = "32.4"
        val resFractional = calculator.isFractional
        val exceptedFractional = true
        assertEquals(expected, res)
        assertEquals(exceptedFractional, resFractional)
    }

    @Test
    fun doublePointTest() {
        num32()
        calculator.point()
        val res = calculator.point()
        val expected = "32."
        val resFractional = calculator.isFractional
        val exceptedFractional = true
        assertEquals(expected, res)
        assertEquals(exceptedFractional, resFractional)
    }

    @Test
    fun negateTest() {
        num32()
        val res = calculator.negate()
        val expected = "-32"
        val resNegative = calculator.isNegative
        val exceptedNegative = true
        assertEquals(expected, res)
        assertEquals(exceptedNegative, resNegative)
    }

    @Test
    fun negateZeroTest() {
        calculator.addZero()
        val res = calculator.negate()
        val expected = "0"
        val resNegative = calculator.isNegative
        val exceptedNegative = false
        assertEquals(expected, res)
        assertEquals(exceptedNegative, resNegative)
    }

    @Test
    fun negateAfterNegateTest() {
        num32()
        calculator.negate()
        calculator.addTwo()
        val res = calculator.negate()
        val expected = "322"
        val resNegative = calculator.isNegative
        val exceptedNegative = false
        assertEquals(expected, res)
        assertEquals(exceptedNegative, resNegative)
    }

    @Test
    fun deleteEmptyTest() {
        val res = calculator.delete()
        val expected = ""
        assertEquals(expected, res)
    }

    @Test
    fun deleteDigitTest() {
        calculator.addThree()
        calculator.addTwo()
        val res = calculator.delete()
        val expected = "3"
        assertEquals(expected, res)
    }

    @Test
    fun deleteOperationTest() {
        num32()
        calculator.multiply()
        val res = calculator.delete()
        val expected = "32"
        assertEquals(expected, res)
    }

    @Test
    fun deleteNegateTest() {
        calculator.addThree()
        calculator.negate()
        val res = calculator.delete()
        val expected = ""
        val resNegative = calculator.isNegative
        val exceptedNegative = false
        assertEquals(expected, res)
        assertEquals(exceptedNegative, resNegative)
    }

    @Test
    fun deletePointTest() {
        calculator.addThree()
        calculator.point()
        val res = calculator.delete()
        val expected = "3"
        val resFractional = calculator.isFractional
        val exceptedFractional = false
        assertEquals(expected, res)
        assertEquals(exceptedFractional, resFractional)
    }

    @Test
    fun equalAddTest() {
        num32()
        calculator.add()
        calculator.addTwo()
        val res = calculator.equal()
        val expected = "34"
        assertEquals(expected, res)
    }

    @Test
    fun equalSubTest() {
        num32()
        calculator.sub()
        calculator.addTwo()
        val res = calculator.equal()
        val expected = "30"
        assertEquals(expected, res)
    }

    @Test
    fun equalMultiplyTest() {
        num32()
        calculator.multiply()
        calculator.addTwo()
        val res = calculator.equal()
        val expected = "64"
        assertEquals(expected, res)
    }

    @Test
    fun equalDivideTest() {
        num32()
        calculator.divide()
        calculator.addTwo()
        val res = calculator.equal()
        val expected = "16"
        assertEquals(expected, res)
    }

    @Test
    fun operationAfterFullExample() {
        num32()
        calculator.divide()
        calculator.addTwo()
        val res = calculator.add()
        val expected = "16"
        assertEquals(expected, res)
    }

    @Test
    fun trapExampleTest() {
        calculator.addZero()
        calculator.point()
        calculator.addOne()
        calculator.add()
        calculator.addZero()
        calculator.point()
        calculator.addTwo()
        val res = calculator.equal()
        val excepted = "0.3"
        assertEquals(excepted, res)
    }

    @Test
    fun numberDivisionByZeroTest() {
        val res = inf()
        val excepted = "Infinity"
        assertEquals(excepted, res)
    }

    @Test
    fun zeroDivisionByZeroTest() {
        val res = nan()
        val excepted = "NaN"
        assertEquals(excepted, res)
    }

    @Test
    fun destructionInfinityTest() {
        inf()
        val res = destruction()
        val excepted = "Infinity"
        assertEquals(excepted, res)
    }

    @Test
    fun destructionNaNTest() {
        nan()
        val res = destruction()
        val excepted = "NaN"
        assertEquals(excepted, res)
    }
}