package com.larten.android.tipCalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("53,00"))))
    }

    @Test
    fun calculate_for_1_person_10_percentage() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("300"))

        onView(withId(R.id.option_ten_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("330,00"))))
    }

    @Test
    fun calculate_for_1_person_3_percentage() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("300"))

        onView(withId(R.id.option_three_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("309,00"))))
    }

    @Test
    fun calculate_for_2_person_10_percentage() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("300"))

        onView(withId(R.id.user2)).perform(click())

        onView(withId(R.id.option_ten_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("165,00"))))
    }

    @Test
    fun calculate_for_2_person_5_percentage() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("300"))

        onView(withId(R.id.user2)).perform(click())

        onView(withId(R.id.option_five_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("158,00"))))
    }

    @Test
    fun calculate_for_2_person_3_percentage() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("300"))

        onView(withId(R.id.user2)).perform(click())

        onView(withId(R.id.option_three_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("155,00"))))
    }

    @Test
    fun calculate_for_2_person_3_percentage_with_comma() {
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("300,00"))

        onView(withId(R.id.user2)).perform(click())

        onView(withId(R.id.option_three_percent)).perform(click())

        onView(withId(R.id.round_up_switch)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("154,50"))))
    }
}