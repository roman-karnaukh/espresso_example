package automation.qa.espressoexample.test;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import automation.qa.espressoexample.*;
import automation.qa.espressoexample.R;
import automation.qa.espressoexample.addition.Constants;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static automation.qa.espressoexample.helpers.Matchers.isLongClickable;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */

@LargeTest
public class ApplicationTest {
    Constants constants;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void precondition() {
        constants = new Constants();
    }

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
    public void positionAssertion(){
        onView(withId(automation.qa.espressoexample.R.id.btnTextInputLayout))
                .perform(click());
        onView(withId(R.id.etEmail)).check(isAbove(withId(R.id.etName)));
        onView(withId(R.id.etPass)).check(isBelow(withId(R.id.etName)));
        onView(withId(R.id.etName)).check(isLongClickable());

    }

}