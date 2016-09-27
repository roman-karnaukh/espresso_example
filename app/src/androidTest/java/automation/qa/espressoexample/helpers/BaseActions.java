package automation.qa.espressoexample.helpers;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Karnaukh Roman on 23.09.2016.
 */
public class BaseActions {

    public void clickOnRecyclerViewItemWithText(int rvId, String text){
        int fromRecycler = DisplayReader.getRecyclerViewItemsCount(rvId);

        int i = 0;
        while(true){
            try{
                onView(withId(rvId))
                        .perform(RecyclerViewActions.scrollToPosition(i));
                onView(withText(text)).perform(click());
                break;
            }catch (NoMatchingViewException err){
                i++;
                if(i == fromRecycler) {
                    throw  err;
                }
            }
        }
    }
}
