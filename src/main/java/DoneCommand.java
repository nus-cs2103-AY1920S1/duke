import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Done Command is a Command, specifically for a done operation.
 */
public class DoneCommand extends Command {

  int index;

  public DoneCommand(int index) {
    this.index = index - 1;
  }

  public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException, IOException {

    if (index <= 0 || index > tasks.getSize() - 1) {
      new InvalidCommand("Integer supplied should be within range of list.").execute(tasks, ui, storage);
      return;
    }

    // Execute Command
    tasks.getTaskList().get(index).setAsDone();

    Task doneTask = tasks.getTaskList().get(index);

    // Printing Output
    ui.showTopBorder();
    System.out.println("\n\tNice! I have marked this task as done: ");
    System.out.println("\n\t" + doneTask);
    ui.showBottomBorder();

    // Saving to file
    storage.updateAsDone(index);

  }
}