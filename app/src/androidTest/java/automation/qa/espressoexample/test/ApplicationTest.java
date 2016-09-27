package automation.qa.espressoexample.test;

import android.content.Context;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import automation.qa.espressoexample.MainActivity;
import automation.qa.espressoexample.R;
import automation.qa.espressoexample.addition.Constants;
import automation.qa.espressoexample.helpers.BaseActions;
import automation.qa.espressoexample.helpers.Driver;
import automation.qa.espressoexample.models.Person;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static automation.qa.espressoexample.helpers.Matchers.isLongClickable;
import static automation.qa.espressoexample.helpers.Matchers.isToast;
import static automation.qa.espressoexample.helpers.Matchers.withPersonName;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

public class ApplicationTest extends Driver {

    @Test
    public void checkHelloWorldDisplayed() {
        onView(withText("Hello world!")).check(matches(isDisplayed()));
    }

    @Test
    public void testInputLayoutActivity() {
        onView(withId(automation.qa.espressoexample.R.id.btnTextInputLayout))
                .perform(click());


        onView(withText("TextInputLayoutActivity"))
                .check(matches(isDisplayed()));

        onView(withId(automation.qa.espressoexample.R.id.etEmail))
                .check(matches(isDisplayed()));

        onView(withId(automation.qa.espressoexample.R.id.etName))
                .check(matches(isDisplayed()));

        onView(withId(automation.qa.espressoexample.R.id.etPass))
                .check(matches(isDisplayed()));

    }

    @Test
    public void positionAssertion() {
        onView(withId(automation.qa.espressoexample.R.id.btnTextInputLayout))
                .perform(click());
        onView(withId(R.id.etEmail)).check(isAbove(withId(R.id.etName)));
        onView(withId(R.id.etPass)).check(isBelow(withId(R.id.etName)));
        onView(withId(R.id.etName)).check(isLongClickable());
    }

    @Test
    public void checkRecyclerView() {
        String name = "Jason Statham";
        onView(withId(R.id.btnRecyclerView)).perform(click());
        ba.clickOnRecyclerViewItemWithText(R.id.rvMain, name);
        onView(withText(containsString(name))).inRoot(isToast()).check(matches(isDisplayed()));
    }

}