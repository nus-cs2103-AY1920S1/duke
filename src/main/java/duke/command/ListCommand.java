package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    Ui.printLine(tasks.printAllTasks());
  }
}
