package com.example.TaskMaster;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import android.content.Context;

import androidx.test.rule.ActivityTestRule;

import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myapplication2", appContext.getPackageName());

    }
    @Rule
    public ActivityTestRule<HomePage> activityRule =
            new ActivityTestRule<>(HomePage.class);

    @Test
    public void listGoesOverTheFold() {
        onView(withText("Hello world!")).check(matches(isDisplayed()));
        onView(withText("Add Task")).check(matches(isDisplayed()));
        onView(withText("SETTINGS")).check(matches(isDisplayed()));
        onView(withId(R.id.rs)).check(matches(isDisplayed()));
    }
    @Test
    public void checkAllDisplayed() {
        onView(withId(R.id.title)).check((matches(isDisplayed())));
        onView(withId(R.id.taskTitle)).check(matches(isDisplayed()));

    }
    @Test
    public void settingsButton(){
        onView(withId(R.id.setting)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.editTextTextPersonName)).check(matches(isDisplayed())).perform(typeText("Aya"));
        onView(withId(R.id.button7)).check(matches(isDisplayed())).perform(click());
        onView(withText("Aya’s tasks")).check(matches(isDisplayed()));
    }
    @Test
    public void addTaskButton() {
        onView(withId(R.id.button3)).check(matches(isDisplayed())).perform(click());
        onView(withId(R.id.taskTitle)).check(matches(isDisplayed())).perform(typeText("ibraheem"));
        onView(withId(R.id.descreption)).check(matches(isDisplayed())).perform(typeText("Espresso doing"));
        onView(withId(R.id.taskstate)).check(matches(isDisplayed())).perform(typeText("Completed"));

        onView(withText("ibraheem")).check(matches(isDisplayed()));
    }
}

