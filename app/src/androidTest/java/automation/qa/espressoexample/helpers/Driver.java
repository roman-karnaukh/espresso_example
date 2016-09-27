package automation.qa.espressoexample.helpers;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.widget.Toast;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import automation.qa.espressoexample.MainActivity;
import automation.qa.espressoexample.addition.Constants;

/**
 * Created by Karnaukh Roman on 26.09.2016.
 */
public class Driver {
    Constants constants;
    Context mContext;
    public BaseActions ba;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void start() {
        constants = new Constants();
        mContext = InstrumentationRegistry.getContext();
        ba = new BaseActions();
    }

    @After
    public void testExecuted() {
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, "Test Executed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
