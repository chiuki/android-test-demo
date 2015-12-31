package com.sqisland.android.test_demo;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

public class DemoApplication extends Application {
  @Singleton
  @Component(modules = ClockModule.class)
  public interface ApplicationComponent extends DemoComponent {
  }

  private final DemoComponent component = createComponent();

  protected DemoComponent createComponent() {
    return DaggerDemoApplication_ApplicationComponent.builder()
        .clockModule(new ClockModule())
        .build();
  }

  public DemoComponent component() {
    return component;
  }
}