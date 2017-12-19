package com.sqisland.android.test_demo;

import android.app.Application;

import dagger.Component;

public class DemoApplication extends Application {
  @Component(modules = ClockModule.class)
  public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
  }

  private volatile ApplicationComponent component = null;

  public ApplicationComponent component() {
    if (component == null) {
      synchronized (this) {
        if (component == null) {
          component = DaggerDemoApplication_ApplicationComponent.builder().build();
        }
      }
    }
    return component;
  }
}