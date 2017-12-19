package com.sqisland.android.test_demo;

import dagger.Module;
import dagger.Provides;

@Module
public class ClockModule {
  @Provides
  static Clock provideClock() {
    return new Clock();
  }
}