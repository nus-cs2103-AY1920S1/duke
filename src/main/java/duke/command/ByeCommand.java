package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
  @Override
  public boolean isExit() {
    return true;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    Ui.printLine("Bye. Hope to see you again soon!");
  }
}
