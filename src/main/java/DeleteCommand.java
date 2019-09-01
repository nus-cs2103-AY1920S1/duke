import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Delete Command is a Command, specifically for delete operation.
 */
public class DeleteCommand extends Command {

  int index;

  public DeleteCommand(int index) {
    this.index = index - 1;
  }

  public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException, IOException {

    if (index < 0 || index > tasks.getSize() - 1) {
      new InvalidCommand("Integer supplied should be within range of list.").execute(tasks, ui, storage);
      return;
    }

    // Execute command
    Task removed = tasks.getTaskList().get(index);
    tasks.getTaskList().remove(index);

    // Printing Output
    ui.showTopBorder();
    System.out.println("\n\tNoted. I have removed this task: ");
    System.out.println("\n\t" + removed);
    System.out.println("\n\tNow you have " + tasks.getSize() + " tasks in the list.");
    ui.showBottomBorder();

    // Save in .txt file
    storage.deleteTask(index);

  }
}