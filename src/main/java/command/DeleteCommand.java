package command;

import java.io.FileNotFoundException;
import java.io.IOException;
import utils.Storage;
import utils.TaskList;
import utils.Ui;
import tasks.Task;

/**
 * Delete Command is a Command, specifically for delete operation.
 */
public class DeleteCommand extends Command {

  int index;

  public DeleteCommand(int index) {
    this.index = index - 1;
  }

  public String execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException, IOException {

    String output = "";

    if (index < 0 || index > tasks.getSize() - 1) {
      return new InvalidCommand("Integer supplied should be within range of list.").execute(tasks, ui, storage);
    }

    // Execute command
    Task removed = tasks.getTaskList().get(index);
    tasks.getTaskList().remove(index);

    // Save output as String
    output += ui.getTopBorder();
    output += "\n\tNoted. I have removed this task: ";
    output += "\n\t" + removed;
    output += "\n\tNow you have " + tasks.getSize() + " tasks in the list.";
    output += ui.getBottomBorder();

    // Save in .txt file
    storage.deleteTask(index);

    return output;

  }
}