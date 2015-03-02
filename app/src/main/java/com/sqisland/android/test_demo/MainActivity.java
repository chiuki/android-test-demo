package com.sqisland.android.test_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

public class MainActivity extends Activity {
  @Inject
  Clock clock;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((DemoApplication) getApplication()).component().inject(this);

    TextView todayView = (TextView) findViewById(R.id.date);
    todayView.setText(DateUtils.format(clock.getNow()));
  }
}