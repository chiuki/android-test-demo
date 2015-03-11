package com.sqisland.android.test_demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.joda.time.DateTime;

import javax.inject.Inject;

public class MainActivity extends Activity {
  public static final String KEY_MILLIS = "millis";

  @Inject
  Clock clock;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ((DemoApplication) getApplication()).component().inject(this);

    TextView todayView = (TextView) findViewById(R.id.date);

    long millis = getIntent().getLongExtra(KEY_MILLIS, -1);
    DateTime dateTime = (millis > 0) ? new DateTime(millis) : clock.getNow();
    todayView.setText(DateUtils.format(dateTime));
  }
}