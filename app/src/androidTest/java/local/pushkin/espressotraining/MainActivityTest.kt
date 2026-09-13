package local.pushkin.espressotraining

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import local.pushkin.espressotraining.matchers.withError
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.BeforeClass
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val myTestRule = CustomTestRule(TEST_RULE_TAG)


    companion object {

        const val JUNIT_TAG = "JUnitTestLifeCycleTag"
        const val TEST_RULE_TAG = "JUnitTestRuleTag"
        const val CLASS_RULE_TAG = "JUnitClassRuleTag"

        @get:ClassRule
        @JvmStatic
        val myClassRule = CustomTestRule(CLASS_RULE_TAG)

        @BeforeClass
        @JvmStatic
        fun setUpClass() {
            println("$JUNIT_TAG : BEFORE CLASS")
        }

        @AfterClass
        @JvmStatic
        fun tearDownClass() {
            println("$JUNIT_TAG : AFTER CLASS")
        }
    }

    @Before
    fun setUp() {
        println("$JUNIT_TAG : BEFORE TEST")
        Intents.init()
    }

    @After
    fun tearDown() {
        println("$JUNIT_TAG : AFTER TEST")
        Intents.release()
    }

    @Test
    fun enterNameAndCheckResultTest() {
        onView(withId(R.id.edit_text_name))
            .perform(typeText("Alexander"))

        onView(withId(R.id.button_hello))
            .perform(click())

        onView(withId(R.id.text_result))
            .check(matches(withText("Alexander")))
    }

    @Test
    fun clickOnMurzikAdapterViewTest() {
        onData(equalTo("Murzik"))
            .inAdapterView(withId(R.id.cat_list)) // if there are more than one AdapterView
            .perform(click())
        onView(withId(R.id.text_result))
            .check(matches(withText("Murzik")))
    }

    @Test
    fun clickOnCatRecyclerViewTest() {
        onView(withId(R.id.cat_recycler_view))
            .perform(
                RecyclerViewActions.scrollToPosition<CatAdapter.CatViewHolder>(99)
            )

        onView(withId(R.id.cat_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<CatAdapter.CatViewHolder>(
                    /* itemViewMatcher = */ withText("Cat 99"),
                    /* viewAction = */ click()
                )
            )
    }

    @Test
    fun emptyNameShouldShowsErrorTest() {
        onView(withId(R.id.edit_text_name))
            .perform(replaceText(""))

        onView(withId(R.id.button_hello))
            .perform(click())

        onView(withId(R.id.edit_text_name))
            .check(matches(withError("Input a name")))
    }

    @Test
    fun alertDialogIsDisplayedTest() {
        onView(withId(R.id.button_show_alert_dialog))
            .perform(click())

        onView(withText("Confirmation"))
            .check(matches(isDisplayed()))

        onView(withText("Are you sure"))
            .check(matches(isDisplayed()))

        onView(withText("OK"))
            .check(matches(isDisplayed()))

        onView(withText("Cancel"))
            .check(matches(isDisplayed()))
    }

    // Now Tast possibly should be matched with UiAutomator
    // androidx.test.espresso.NoMatchingRootException: Matcher 'with decor view not is <DecorView@3c47544[MainActivity]>' did not match any of the following roots:
    @Test
    fun toastIsDisplayed() {
        var decorView: View? = null

        activityScenarioRule.scenario.onActivity { activity ->
            decorView = activity.window.decorView
        }

        onView(withId(R.id.button_show_toast))
            .perform(click())

        onView(withText("Hello from Toast"))
            .inRoot(
                withDecorView(
                    not(`is`(decorView))
                )
            )
            .check(matches(isDisplayed()))
    }

    @Test
    fun openGoogleIntendedTest() {
        onView(withId(R.id.button_open_google))
            .perform(click())

        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://google.com"))
            )
        )
    }

    @Test
    fun openGoogleIntendingTest() {

        intending(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://google.com"))
            )
        ).respondWith(
            Instrumentation.ActivityResult(
                Activity.RESULT_OK,
                null
            )
        )

        onView(withId(R.id.button_open_google))
            .perform(click())

        intended(
            allOf(
                hasAction(Intent.ACTION_VIEW),
                hasData(Uri.parse("https://google.com"))
            )
        )
    }

    @Test
    fun openSecondActivityIntentTest() {
        onView(withId(R.id.button_open_second_activity))
            .perform(click())

        intended(
            hasComponent(SecondActivity::class.java.name)
        )
    }
}