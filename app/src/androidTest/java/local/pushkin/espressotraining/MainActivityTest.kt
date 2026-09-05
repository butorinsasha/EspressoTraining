package local.pushkin.espressotraining

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.equalTo
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

    @Test
    fun clickOnMursikAdapterViewTest() {
        onData(equalTo("Cat 49"))
            .inAdapterView(withId(R.id.catList))
            .perform(click())
        onView(withId(R.id.textResult))
            .check(matches(withText("Cat 49")))
    }

    @Test
    fun clickOnMursikAdapterViewTest2() {
        onView(withText("Cat 49"))
            .perform(click())
        onView(withId(R.id.textResult))
            .check(matches(withText("Cat 49")))
    }

    @Test
    fun clickOnCatRecyclerViewTest() {
        onView(withId(R.id.catRecyclerView))
            .perform(
                RecyclerViewActions.scrollToPosition<CatAdapter.CatViewHolder>(99)
            )

        onView(withId(R.id.catRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<CatAdapter.CatViewHolder>(
                    /* position = */ 99,
                    /* viewAction = */ click()
                )
            )
    }

}