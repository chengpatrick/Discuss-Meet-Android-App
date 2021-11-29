package com.example.checkpoint3for5236;

import static org.junit.Assert.*;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddClassActivityTest {
    @Rule
    public ActivityTestRule<AddClassActivity> addClassActivityActivityTestRule = new ActivityTestRule<AddClassActivity>(AddClassActivity.class);

    private AddClassActivity addClassActivity = null;

    @Before
    public void setUp() throws Exception {
        addClassActivity = addClassActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = addClassActivity.findViewById(R.id.textView8);
        assertNotNull(view);
    }

    @Test
    public void testAddClassButton(){
        assertNotNull(addClassActivity.findViewById(R.id.addButton));
        ViewInteraction button = Espresso.onView(ViewMatchers.withId(R.id.addButton));
        ViewInteraction text = Espresso.onView(ViewMatchers.withId(R.id.editTextTextClassName));

        button.perform(ViewActions.click());
        text.check(ViewAssertions.matches(ViewMatchers.hasErrorText("Class name is required")));
    }

    @After
    public void tearDown() throws Exception {
        addClassActivity = null;
    }
}