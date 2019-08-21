package seedu.duke;

import seedu.duke.PrettyPrint;
import java.util.ArrayList;

public class EventHandler {

  private ArrayList<Task> todoList;

  public EventHandler() {
    this.todoList = new ArrayList<Task>();
  }

  public void run(String command) {
    String[] tokens = command.split(" ", 2);
    switch (tokens[0]) {
      case "bye":
        return;
      case "list":
        list(command);
        break;
      case "done":
        markAsDone(Integer.parseInt(tokens[1]));
        break;
      default:
        add(command);
        break;
    }
  }

  private void markAsDone(int index) {

    // EXCEPTION: WHEN INDEX IS OUT OF RANGE
    if (index < 1 || index > todoList.size()) {
      String errorMessage = String.format(
        "There is no task number #%d. Please enter a range between 1 and %d.",
        index, todoList.size());
      PrettyPrint.printBlock(new String[] {errorMessage});
      return;
    }

    todoList.get(index - 1).markAsDone();
    String[] outputs = new String[2];
    outputs[0] = "Nice! I've marked this task as done: ";
    outputs[1] = "  " + todoList.get(index - 1).toString();
    PrettyPrint.printBlock(outputs);
  }

  private void add(String todo) {
    todoList.add(new Task(todo));
    String output = "added: " + todo;
    PrettyPrint.printBlock(new String[] {output});
  }

  private void list(String command) {
    PrettyPrint.printBlock(toStringArray());
  }

  // Converts todoList into a String Array
  private String[] toStringArray() {
    String[] stringArray = new String[todoList.size()];
    for (int z = 0; z < todoList.size(); z++) {
      stringArray[z] = String.format("%3d." + todoList.get(z).toString(), z + 1);
    }
    return stringArray;
  }

}
