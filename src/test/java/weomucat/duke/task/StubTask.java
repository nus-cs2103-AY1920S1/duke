package weomucat.duke.task;

import weomucat.duke.date.Date;
import weomucat.duke.ui.Message;

public class StubTask extends Task {

  public StubTask(String description) {
    super(description);
  }

  @Override
  public Message toMessage() {
    return null;
  }

  @Override
  boolean isOverDue() {
    return false;
  }

  @Override
  public int compareTo(Date date) {
    return 0;
  }
}
