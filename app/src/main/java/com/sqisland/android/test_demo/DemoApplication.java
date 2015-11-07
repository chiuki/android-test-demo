package com.sqisland.android.test_demo;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import android.support.annotation.VisibleForTesting;

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

  @VisibleForTesting void setComponent(DemoComponent component) {
    this.component = component;
  }

  public DemoComponent component() {
    return component;
  }
}
