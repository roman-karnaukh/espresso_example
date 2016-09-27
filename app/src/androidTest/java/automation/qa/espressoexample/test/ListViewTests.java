package automation.qa.espressoexample.test;

import org.junit.Before;
import org.junit.Test;

import automation.qa.espressoexample.helpers.Driver;
import automation.qa.espressoexample.models.Person;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static automation.qa.espressoexample.helpers.Matchers.isToast;
import static automation.qa.espressoexample.helpers.Matchers.withPersonName;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Karnaukh Roman on 26.09.2016.
 */
public class ListViewTests extends Driver {
    String name = "Jason Statham";
    String age = "49 years old";

    @Before
    public void goToListViewActivity() {
        onView(withId(automation.qa.espressoexample.R.id.btnListViewActivity)).perform(click());
    }

    @Test
    public void checkClickOnElementByName() throws InterruptedException {
        onData(allOf(instanceOf(Person.class), withPersonName(is(name))))
                .perform(click());
        onView(withText(containsString(name))).inRoot(isToast()).check(matches(isDisplayed()));
    }

    @Test
    public void checkClickOnElementByAge() {
        onData(allOf(instanceOf(Person.class), withPersonName(is(name))))
                .onChildView(withId(automation.qa.espressoexample.R.id.person_age))
                .perform(click());
        onView(withText(containsString(age))).inRoot(isToast()).check(matches(isDisplayed()));
    }

    @Test
    public void checkClickOnElementByPosition() {
        onData(anything())
                .inAdapterView(withClassName(containsString("ListView")))
                .atPosition(5)
                .onChildView(withId(automation.qa.espressoexample.R.id.person_name))
                .perform(click());
        onView(withText(containsString(name))).inRoot(isToast()).check(matches(isDisplayed()));
    }
}
