package com.sqisland.android.test_demo;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ClockModule {
  @Provides
  @Singleton
  Clock provideClock() {
    return new Clock();
  }
}