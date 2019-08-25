package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
  private Task task;

  public AddCommand(String typeOfTask, String name, String datetime) {
    switch (typeOfTask) {
    case "todo":
      task = new ToDo(name);
      break;
    case "deadline":
      task = new Deadlines(name, datetime);
      break;
    case "event":
      task = new Event(name, datetime);
      break;
    default:
      break;
    }
  }

  public AddCommand(String typeOfEvent, String name) {
    this(typeOfEvent, name, null);
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    tasks.add(task);
    storage.addTask(task);
    Ui.printLine("Got it. I've added this task: ");
    Ui.printLine("  " + task);
    Ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (obj instanceof AddCommand) {
      AddCommand other = (AddCommand) obj;
      return other.task.equals(this.task);
    }
    return false;
  }
}
