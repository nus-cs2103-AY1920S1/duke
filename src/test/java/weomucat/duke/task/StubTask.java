package weomucat.duke.task;

import weomucat.duke.ui.Message;

public class StubTask extends Task {

  public StubTask(String description) {
    super(description);
  }

  @Override
  public Message toMessage() {
    return null;
  }
}
