package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * AddCommand class adds a task to the list.
 *
 * @author scwaterbear
 */
public class AddCommand extends Command {

  private Task task;

  /**
   * Class Constructor.
   *
   * @param task task to add.
   */
  public AddCommand(Task task) {
    this.task = task;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    tasks.addTask(task);

    System.out.println("Got it. I've added this task:");
    System.out.println(task);
    if (tasks.getSize() == 1) {
      System.out.println("Now you have 1 task in the list.");
    } else {
      System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
    }

    persistState(tasks, storage);
  }
}
