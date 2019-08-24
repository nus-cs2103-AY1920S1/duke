package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
  private int index;

  public DeleteCommand(int index) {
    this.index = index;
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    Task deleted = tasks.remove(index);
    storage.deleteLine(deleted.storageString());
    Ui.printLine("Noted. I've removed this task:");
    Ui.printLine("  " + deleted);
    Ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
  }
}
