package com.calculator

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.UiDevice

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private var device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    private val wait = 5000.toLong()

    private fun longNum() {
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.three)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.four)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.five)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.six)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.seven)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.eight)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.nine)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
    }

    private fun nan() {
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.divide)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
    }

    private fun inf() {
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.divide)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
    }

    private fun zeroPoint() {
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.point)).perform(ViewActions.click())
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.calculator", appContext.packageName)
    }

    @Test
    fun addZeroTest() {
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("0")))
    }

    @Test
    fun addOneTest() {
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

    @Test
    fun addTwoTest() {
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))
    }

    @Test
    fun addThreeTest() {
        Espresso.onView(withId(R.id.three)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("3")))
    }

    @Test
    fun addFourTest() {
        Espresso.onView(withId(R.id.four)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("4")))
    }

    @Test
    fun addFiveTest() {
        Espresso.onView(withId(R.id.five)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("5")))
    }

    @Test
    fun addSixTest() {
        Espresso.onView(withId(R.id.six)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("6")))
    }

    @Test
    fun addSevenTest() {
        Espresso.onView(withId(R.id.seven)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("7")))
    }

    @Test
    fun addEightTest() {
        Espresso.onView(withId(R.id.eight)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("8")))
    }

    @Test
    fun addNineTest() {
        Espresso.onView(withId(R.id.nine)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("9")))
    }

    @Test
    fun longNumberTest() {
        longNum()
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("1234567890")))
    }

    @Test
    fun deleteAllTest() {
        longNum()
        Espresso.onView(withId(R.id.multiply)).perform(ViewActions.click())
        longNum()
        Espresso.onView(withId(R.id.all_Delete)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    @Test
    fun addEqualTest() {
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.add)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.five)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("5")))
    }


    @Test
    fun multiplyEqualTest() {
        Espresso.onView(withId(R.id.seven)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.multiply)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.seven)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.nine)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("553")))
    }

    @Test
    fun subEqualTest() {
        Espresso.onView(withId(R.id.five)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.five)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.sub)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("44")))
    }

    @Test
    fun divideEqualTest() {
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.divide)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.four)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("0.25")))
    }

    @Test
    fun pointTest() {
        zeroPoint()
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("0.1")))
    }

    @Test
    fun doublePointTest() {
        zeroPoint()
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.point)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("0.11")))
    }

    @Test
    fun negateTest() {
        longNum()
        Espresso.onView(withId(R.id.negate)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("-1234567890")))
    }

    @Test
    fun doubleNegateTest() {
        longNum()
        Espresso.onView(withId(R.id.negate)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.negate)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("1234567890")))
    }

    @Test
    fun deletePointTest() {
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.point)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.delete)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.delete)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

    @Test
    fun divisionZeroByZeroTest() {
        nan()
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("NaN")))
    }

    @Test
    fun divisionNumByZeroTest() {
        inf()
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("Infinity")))
    }

    @Test
    fun landTest() {
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.add)).perform(ViewActions.click())
        device.setOrientationLeft()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        device.setOrientationRight()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("4")))
    }

    @Test
    fun complexTest1() {
        Espresso.onView(withId(R.id.eight)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.point)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.negate)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.add)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.zero)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.point)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        device.setOrientationLeft()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("-8")))
    }

    @Test
    fun complexTest2() {
        nan()
        device.setOrientationLeft()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.delete)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.delete)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.add)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.point)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        device.setOrientationRight()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("NaN")))
    }

    @Test
    fun complexTest3() {
        inf()
        Espresso.onView(withId(R.id.negate)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.divide)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.delete)).perform(ViewActions.click())
        device.setOrientationLeft()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.divide)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        device.setOrientationRight()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("-Infinity")))
    }

    @Test
    fun complexTest4() {
        zeroPoint()
        Espresso.onView(withId(R.id.one)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.add)).perform(ViewActions.click())
        zeroPoint()
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        device.setOrientationLeft()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        device.setOrientationRight()
        device.waitForWindowUpdate(null, wait)
        device.setOrientationRight()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.multiply)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.two)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.add)).perform(ViewActions.click())
        zeroPoint()
        Espresso.onView(withId(R.id.four)).perform(ViewActions.click())
        device.setOrientationLeft()
        device.waitForWindowUpdate(null, wait)
        Espresso.onView(withId(R.id.equal)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.exp))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }
}