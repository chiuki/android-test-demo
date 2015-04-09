package com.sqisland.android.test_demo;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Modified from https://gist.github.com/JakeWharton/1c2f2cadab2ddd97f9fb to allow setting a
 * different intent per test method.
 */
public class ActivityLauncher<T extends Activity> {
  @Singleton
  @Component(modules = MockClockModule.class)
  public interface TestComponent extends DemoComponent {
    void inject(MainActivityTest mainActivityTest);
  }

  private final Class<T> activityClass;
  private IntentModifier intentModifier;

  private T activity;
  private Instrumentation instrumentation;

  public ActivityLauncher(Class<T> activityClass) {
    this.activityClass = activityClass;

    DemoApplication app
        = (DemoApplication) fetchInstrumentation().getTargetContext().getApplicationContext();
    TestComponent component = DaggerActivityLauncher_TestComponent.builder()
        .mockClockModule(new MockClockModule())
        .build();
    app.setComponent(component);
  }

  public DemoApplication getApplication() {
    return (DemoApplication) fetchInstrumentation().getTargetContext().getApplicationContext();
  }

  protected Intent getLaunchIntent(String targetPackage, Class<T> activityClass) {
    Intent intent = new Intent(Intent.ACTION_MAIN);
    intent.setClassName(targetPackage, activityClass.getName());
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

    if (intentModifier != null) {
      intentModifier.modify(intent);
    }

    return intent;
  }

  public void setLaunchIntentModifier(IntentModifier intentModifier) {
    this.intentModifier = intentModifier;
  }

  public interface IntentModifier {
    void modify(Intent intent);
  }

  /**
   * Get the running instance of the specified activity. This will launch it if it is not already
   * running.
   */
  public final T getActivity() {
    launchActivity();
    return activity;
  }

  /** Get the {@link Instrumentation} instance for this test. */
  public final Instrumentation instrumentation() {
    launchActivity();
    return instrumentation;
  }

  public void tearDown() {
    if (!activity.isFinishing()) {
      activity.finish();
    }
    activity = null; // Eager reference kill in case someone leaked our reference.
  }

  private Instrumentation fetchInstrumentation() {
    Instrumentation result = instrumentation;
    return result != null ? result
        : (instrumentation = InstrumentationRegistry.getInstrumentation());
  }

  @SuppressWarnings("unchecked") // Guarded by generics at the constructor.
  private void launchActivity() {
    if (activity != null) {
      return;
    }

    Instrumentation instrumentation = fetchInstrumentation();

    String targetPackage = instrumentation.getTargetContext().getPackageName();
    Intent intent = getLaunchIntent(targetPackage, activityClass);

    activity = (T) instrumentation.startActivitySync(intent);
    instrumentation.waitForIdleSync();
  }
}