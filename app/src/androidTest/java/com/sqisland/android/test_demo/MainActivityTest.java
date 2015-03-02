package com.sqisland.android.test_demo;

import android.support.test.runner.AndroidJUnit4;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import javax.inject.Inject;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  @Inject
  Clock clock;

  protected ActivityLauncher launcher;

  @Before
  public void setUp() {
    launcher = new ActivityLauncher(MainActivity.class);
    ((ActivityLauncher.TestComponent) launcher.getApplication().component()).inject(this);
  }

  @After
  public void tearDown() {
    launcher.tearDown();
    launcher = null;
  }

  @Test
  public void today() {
    Mockito.when(clock.getNow()).thenReturn(new DateTime(2008, 9, 23, 0, 0, 0));

    launcher.getActivity();

    onView(withId(R.id.date))
        .check(matches(withText("2008-09-23")));
  }
}