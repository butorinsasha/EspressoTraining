package local.pushkin.espressotraining

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun enterNameAndCheckResultTest() {

        onView(withId(R.id.editTextName))
            .perform(typeText("Alexander"))

        onView(withId(R.id.buttonHello))
            .perform(click())

        onView(withId(R.id.textResult))
            .check(matches(withText("Alexander")))
    }
}