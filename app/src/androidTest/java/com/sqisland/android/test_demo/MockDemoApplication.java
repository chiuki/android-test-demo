package com.sqisland.android.test_demo;

public class MockDemoApplication extends DemoApplication {
  @Override
  protected DemoComponent createComponent() {
    return DaggerMainActivityTest_TestComponent.builder()
        .mockClockModule(new MockClockModule())
        .build();
  }
}