package local.pushkin.espressotraining

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
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
    }

    @After
    fun tearDown() {
        println("$JUNIT_TAG : AFTER TEST")
    }

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
    fun clickOnMurzikAdapterViewTest() {
        onData(equalTo("Murzik"))
            .inAdapterView(withId(R.id.catList)) // if there are more than one AdapterView
            .perform(click())
        onView(withId(R.id.textResult))
            .check(matches(withText("Murzik")))
    }

    @Test
    fun clickOnCatRecyclerViewTest() {
        onView(withId(R.id.catRecyclerView))
            .perform(
                RecyclerViewActions.scrollToPosition<CatAdapter.CatViewHolder>(99)
            )

        onView(withId(R.id.catRecyclerView))
            .perform(
                RecyclerViewActions.actionOnItem<CatAdapter.CatViewHolder>(
                    /* itemViewMatcher = */ withText("Cat 99"),
                    /* viewAction = */ click()
                )
            )
    }

    @Test
    fun emptyNameShouldShowsErrorTest() {
        onView(withId(R.id.editTextName))
            .perform(replaceText(""))

        onView(withId(R.id.buttonHello))
            .perform(click())

        onView(withId(R.id.editTextName))
            .check(matches(withError("Input a name")))
    }
}