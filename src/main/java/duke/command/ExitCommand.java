package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * ExitCommand class exits the program.
 *
 * @author scwaterbear
 */
public class ExitCommand extends Command {

  /**
   * Class Constructor.
   */
  public ExitCommand() {
  }

  @Override
  public boolean isExit() {
    return true;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    System.out.println("Bye. Hope to see you again soon!");
  }


}
