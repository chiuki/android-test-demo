package com.sqisland.android.test_demo;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

public class DemoApplication extends Application {
  @Singleton
  @Component(modules = ClockModule.class)
  public interface ApplicationComponent extends DemoComponent {
  }

  private DemoComponent component = null;

  @Override public void onCreate() {
    super.onCreate();
    if (component == null) {
      component = DaggerDemoApplication_ApplicationComponent.builder()
          .clockModule(new ClockModule())
          .build();
    }
  }

  public void setComponent(DemoComponent component) {
    this.component = component;
  }

  public DemoComponent component() {
    return component;
  }
}