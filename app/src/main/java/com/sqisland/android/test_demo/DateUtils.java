package com.sqisland.android.test_demo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public abstract class DateUtils {
  public static final DateTimeFormatter SHORT_DATE = DateTimeFormat.forPattern("yyyy-MM-dd");
  public static String format(DateTime dateTime) {
    return SHORT_DATE.print(dateTime);
  }
}