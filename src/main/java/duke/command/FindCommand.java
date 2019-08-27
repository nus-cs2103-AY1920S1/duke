package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
  private String tofind;

  public FindCommand(String s) {
    this.tofind = s;
  }

  @Override
  public boolean isExit() {
    return false;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    TaskList containsList = tasks.find(this.tofind);
    Ui.printLine("Here are the matching tasks in your list:");
    Ui.printLine(containsList.printAllTasks());
  }
}
