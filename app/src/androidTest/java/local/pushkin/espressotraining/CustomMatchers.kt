package local.pushkin.espressotraining

import android.view.View
import android.widget.EditText
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withError(expectedError: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun matchesSafely(item: View?): Boolean {
            return item is EditText && item.error?.toString() == expectedError
        }

        override fun describeTo(description: Description?) {
            description?.appendText("\"$expectedError\"")
        }

        override fun describeMismatchSafely(item: View?, mismatchDescription: Description?) {
            val editText = item as EditText
            mismatchDescription?.appendText("\"${editText.error}\"")
        }
    }
}

