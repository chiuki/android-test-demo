package com.sqisland.android.test_demo;

public class MockDemoApplication extends DemoApplication {
  private ApplicationComponent component;

  public void setTestComponent(ApplicationComponent component) {
    this.component = component;
  }

  @Override
  public ApplicationComponent component() {
    return component;
  }
}