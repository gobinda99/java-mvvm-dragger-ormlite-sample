package com.gobinda.test;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;

import com.gobinda.test.ui.registerLogin.RegisterLoginActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


/**
 * Created by gobindadeb on 29/1/18.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegisterLoginTest {

    private static final String EMAIL = System.currentTimeMillis() + "test@test.com";
    private static final String PASSWORD = "nQgRT9p3";


    @Rule
    public ActivityTestRule<RegisterLoginActivity> mActivityRule = new ActivityTestRule<>(
            RegisterLoginActivity.class);

    /*
    * Create account test
    * */
    @Test
    public void test1() {

        onView(withId(R.id.email))
                .perform(typeText(EMAIL), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(PASSWORD), closeSoftKeyboard());

        onView(withId(R.id.email)).check(matches(withText(EMAIL)));


        onView(withId(R.id.createAccount)).perform(click());

        onView(withId(R.id.email)).check(matches(withText("")));
        onView(withId(R.id.password)).check(matches(withText("")));

    }


    /**
     * Login test
     */
    @Test
    public void test2() {

        onView(withId(R.id.email))
                .perform(typeText(EMAIL), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(PASSWORD), closeSoftKeyboard());

        onView(withId(R.id.email)).check(matches(withText(EMAIL)));


        onView(withId(R.id.login)).perform(click());

        onView(withText(R.string.login_successful))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).perform(click());


    }

    /**
     * Duplicate account recreate test
     */
    @Test
    public void test3() {

        onView(withId(R.id.email))
                .perform(typeText(EMAIL), closeSoftKeyboard());

        onView(withId(R.id.password))
                .perform(typeText(PASSWORD), closeSoftKeyboard());

        onView(withId(R.id.email)).check(matches(withText(EMAIL)));

        onView(withId(R.id.createAccount)).perform(click());

        onView(withId(R.id.email)).check(matches(withText(EMAIL)));

    }
}
