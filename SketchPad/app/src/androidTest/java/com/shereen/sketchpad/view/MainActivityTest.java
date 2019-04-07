package com.shereen.sketchpad.view;

import android.Manifest;
import android.content.Intent;
import android.view.View;

import com.shereen.sketchpad.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Created by shereen on 4/7/19
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(MainActivity.class, true, false);

    @Rule public GrantPermissionRule writePermissionRule = GrantPermissionRule.grant(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    @Rule public GrantPermissionRule readPermissionRule = GrantPermissionRule.grant(Manifest.permission.READ_EXTERNAL_STORAGE);

    @Test
    public void shouldRenderView() throws Exception {
        rule.launchActivity(new Intent());

        onView(withText("Hello from C++")).check(matches(isDisplayed()));
    }

    @Test
    public void menusRendered(){
        rule.launchActivity(new Intent());

        onView(withId(R.id.backgroundSpinner)).check(matches(isDisplayed()));
        onView(withId(R.id.pencilSpinner)).check(matches(isDisplayed()));
        onView(withId(R.id.saveButton)).check(matches(isDisplayed()));
    }

    @Test
    public void saveToGallery(){
        rule.launchActivity(new Intent());

        ViewInteraction saveButton = onView(withId(R.id.saveButton));
        saveButton.perform(click());
        onView(withText(R.string.name_sketch));  //dialog view
        onView(withId(android.R.id.button2)).perform(click()); // cancel

        saveButton.perform(click());
        onView(withText(R.string.name_sketch));  //dialog view
//        onView(withText(R.string.sketch_hint)).perform(click()).perform(replaceText("Atest")); //sketch hint
        onView(withId(android.R.id.button1));
    }

}