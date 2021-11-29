package com.example.checkpoint3for5236;

import static org.junit.Assert.*;

import android.app.Instrumentation;
import android.content.Intent;
import android.view.View;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.Espresso;
import static androidx.test.espresso.Espresso.onView;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mainActivity = null;

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mainActivity.findViewById(R.id.textView2);
        assertNotNull(view);
    }

    @Test
    public void testOnButtonClick(){
        assertNotNull(mainActivity.findViewById(R.id.signinButton));
        ViewInteraction button = Espresso.onView(ViewMatchers.withId(R.id.signinButton));
        ViewInteraction text = Espresso.onView(ViewMatchers.withId(R.id.editTextTextEmailAddress2));

        button.check(ViewAssertions.matches(ViewMatchers.withText("Log In")));
        button.perform(ViewActions.click());
        text.check(ViewAssertions.matches(ViewMatchers.hasErrorText("Email is required!")));
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}