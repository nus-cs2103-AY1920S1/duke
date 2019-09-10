package weomucat.duke.date;

import weomucat.duke.exception.InvalidParameterException;

/**
 * An Event is a duration between two Dates.
 */
public class Event {

  private static final String DELIMETER = "-";

  private Date from;
  private Date to;

  public Event(String event) throws InvalidParameterException {
    String[] dates = event.split(DELIMETER);
    this.from = new Date(dates[0]);
    this.to = new Date(dates[1]);
  }
}
