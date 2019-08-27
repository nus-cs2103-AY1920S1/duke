package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a List Command
 */
public class ListCommand extends Command {

  /**
   * Method that returns true only if this is an instance of an ExitCommand.
   * @return false
   */
  @Override
  public boolean isExit() {
    return false;
  }

  /**
   * Executes the Command: Prints out tasks in TaskList
   * @param tasks current TaskList instance
   * @param ui current UI instance
   * @param storage current Storage instance
   * @throws DukeException
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    Ui.printLine(tasks.printAllTasks());
  }

  /**
   * Method that checks whether this instance is logically equivalent to another Object
   * @param obj The other object in question.
   * @return true if logically equivalent, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    else if (obj instanceof ListCommand)
      return true;
    return false;
  }
}
