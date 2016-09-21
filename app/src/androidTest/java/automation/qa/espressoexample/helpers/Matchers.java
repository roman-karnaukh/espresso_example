package automation.qa.espressoexample.helpers;

import android.os.IBinder;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TextInputLayout;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.Root;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Karnaukh Roman on 21.09.2016.
 */
public class Matchers {
    private Matchers() {
    }

    /**
     * for clicking on element after taking it position
     */
    public static ViewAction clickXY(final float x, final float y) {
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {
                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);
                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};
                        return coordinates;
                    }
                },
                Press.FINGER);
    }

    public static ViewAction clickXY(final int x, final int y){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }

    public static ViewAction showBottomSheet() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(LinearLayout.class));
            }

            @Override
            public String getDescription() {
                return "Drag bottom sheet";
            }

            @Override
            public void perform(UiController uiController, View view) {
                LinearLayout bottomSheet =  (LinearLayout) view;
                BottomSheetBehavior mBottomSheetBehavior;

                mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        };
    }

    public static View returnView(Matcher<View> matcher) {
        final View[] viewHolder = new View[] { null };
        onView(matcher).perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(View.class);
            }

            @Override
            public String getDescription() {
                return "getting view by matcher ";
            }

            @Override
            public void perform(UiController uiController, View view){
                viewHolder[0] = view;
            }
        });
        return viewHolder[0];
    }


    public static class ToastMatcher extends TypeSafeMatcher<Root> {

        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    // windowToken == appToken means this window isn't contained by any other windows.
                    // if it was a window for an activity, it would have TYPE_BASE_APPLICATION.
                    return true;
                }
            }
            return false;
        }

    }

    public static Matcher<View> withTagIndex(final int index) {
        return new BoundedMatcher<View, TabWidget>(TabWidget.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("with item hint: " );
            }

            @Override
            protected boolean matchesSafely(TabWidget tabWidget) {
                return (tabWidget.getChildTabViewAt(index)).getClass() == FrameLayout.class;
            }

        };

    }

    public static Matcher<View> withInputLayoutHintText(final Matcher<String> stringMatcher) {
        return new BoundedMatcher<View, TextInputLayout>(TextInputLayout.class) {
            @Override
            public boolean matchesSafely(TextInputLayout view) {
                CharSequence hint = view.getHint().toString();
                return stringMatcher.matches(hint);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with InputLayout hint text: " + stringMatcher.toString());
            }
        };
    }



    public static Matcher<View> withInputLayoutHintText(final String string) {
        return new BoundedMatcher<View, TextInputLayout>(TextInputLayout.class) {
            @Override
            public boolean matchesSafely(TextInputLayout view) {
                CharSequence hint = view.getHint().toString();
                return hint.equals(string);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with InputLayout hint text: " + string);
            }
        };
    }


    public static Matcher<View > readConfigsHints(final ArrayList<CharSequence > arrayList) {
        return new BoundedMatcher<View, TextInputLayout>(TextInputLayout.class) {
            @Override
            public boolean matchesSafely(TextInputLayout view) {
                CharSequence hint = view.getHint().toString();
                arrayList.add(hint);
                return hint.equals(anything());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with InputLayout hint text: " + null);
            }
        };
    }


    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static ArrayList<String> readConfigs(int LinearLayoutWidget ) {
        final ArrayList<String> arrayList = new ArrayList<>();
        onView(withId(LinearLayoutWidget)).perform( new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(LinearLayout.class));
            }

            @Override
            public String getDescription() {
                return "Change view text";
            }

            @Override
            public void perform(UiController uiController, View view) {
                LinearLayout linearLayoutView = (LinearLayout) view;
                int count = linearLayoutView.getChildCount();

                for(int index = 0; index<count; index ++ ){
                    TextInputLayout textInputLayout = (TextInputLayout) linearLayoutView.getChildAt(index);
                    String hint = textInputLayout.getHint().toString();
                    arrayList.add(hint);
                }
            }
        });
        return arrayList;
    }

    public static void clearError(String hint){
        onView(withInputLayoutHintText(hint)).perform( new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(LinearLayout.class));
            }

            @Override
            public String getDescription() {
                return "Clear error ";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextInputLayout linearLayoutView = (TextInputLayout) view;
                linearLayoutView.setError(null);
            }
        });
    }

    public static ViewAction clearError(){
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(LinearLayout.class));
            }

            @Override
            public String getDescription() {
                return "Clear error ";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextInputLayout linearLayoutView = (TextInputLayout) view;
                linearLayoutView.setError(null);
            }
        };
    }

    public static ViewAction checkErrorShown(){
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextInputLayout.class));
            }

            @Override
            public String getDescription() {
                return "check error shown ";
            }

            @Override
            public void perform(UiController uiController, View view) {
                TextInputLayout textInputLayout = (TextInputLayout) view;
                assertTrue(!TextUtils.isEmpty(textInputLayout.getError()));

            }
        };
    }


    public static Matcher<Object> backgroundShouldHaveColor(int expectedColor) {
        return buttonShouldHaveBackgroundColor(equalTo(expectedColor));
    }

    private static Matcher<Object> buttonShouldHaveBackgroundColor(final Matcher<Integer> expectedObject) {
        final int[] color = new int[1];
        return new BoundedMatcher<Object, TextView>( TextView.class) {
            @Override
            public boolean matchesSafely(final TextView t) {

                color[0] = t.getCurrentTextColor();


                if( expectedObject.matches(color[0])) {
                    return true;
                } else {
                    return false;
                }
            }

            @Override
            public void describeTo(final Description description) {
                // Should be improved!
                description.appendText("Color did not match "+color[0]);
            }
        };
    }

    public static ViewAssertion isLongClickable() {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e){
                boolean isLongClickable = view.isLongClickable();
                assertFalse(isLongClickable);
            }
        };
    }

}
