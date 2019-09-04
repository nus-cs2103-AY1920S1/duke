package command;

import utils.TaskList;
import utils.Storage;
import utils.Ui;

public class ListCommand extends Command {

  public String execute(TaskList tasks, Ui ui, Storage storage) {

    String output = "";

    // Save output as String
    output += ui.getTopBorder();
    output += "\n\tHere are the tasks in your list: ";
    output += ui.getTasksAsString(tasks.getTaskList());
    output += ui.getBottomBorder();
    return output;
  }

}