package seedu.duke;

import seedu.duke.PrettyPrint;

public class EventHandler {

  public EventHandler() {
  }

  public void run(String command) {
    switch(command) {
      case "exit":
        return;
      default:
        PrettyPrint.printBlock(command);
        break;
    }
  }
}
