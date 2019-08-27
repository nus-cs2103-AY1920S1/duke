package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a Delete Command
 */
public class DeleteCommand extends Command {
  private int index;

  public DeleteCommand(int index) {
    this.index = index;
  }

  /**
   * Method that returns true only if this is an instance of an ExitCommand.
   * @return false
   */
  @Override
  public boolean isExit() {
    return false;
  }

  /**
   * Executes the Command: removes current task from TaskList and removes line from storage.
   * @param tasks current TaskList instance
   * @param ui current UI instance
   * @param storage current Storage instance
   * @throws DukeException
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    Task deleted = tasks.remove(index);
    storage.deleteLine(deleted.storageString());
    Ui.printLine("Noted. I've removed this task:");
    Ui.printLine("  " + deleted);
    Ui.printLine("Now you have " + tasks.size() + " tasks in the list.");
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
    else if (obj instanceof DeleteCommand) {
      DeleteCommand other = (DeleteCommand) obj;
      return other.index == this.index;
    }
    return false;
  }
}
