package seedu.duke;

import seedu.duke.PrettyPrint;
import java.util.ArrayList;

public class EventHandler {

  private ArrayList<String> TodoList;

  public EventHandler() {
    TodoList = new ArrayList<String>();
  }

  public void run(String command) {
    switch(command) {
      case "bye":
        return;
      case "list":
        list(command);
        break;
      default:
        add(command);
        break;
    }
  }

  private void add(String todo) {
    TodoList.add(todo);
    String output = "added: " + todo;
    PrettyPrint.printBlock(new String[] {output});
  }

  private void list(String command) {
    String[] outputs = TodoList.toArray(new String[TodoList.size()]);
    for (int z = 0; z < TodoList.size(); z++) {
      outputs[z] = String.format("%3d. " + outputs[z], z + 1);
    }
    PrettyPrint.printBlock(outputs);
  }

}
