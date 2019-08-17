package command;

import task.TaskList;
import ui.Ui;

/**
 * Class representing a command to list items in a task list.
 */
public class ListCommand extends Command {
    public void execute(TaskList tl, Ui ui) {
        ui.printMessage("Here are the tasks in your list:");
        for (int i = 1; i <= tl.size(); i++) {
            ui.printMessage(i + ". " + tl.get(i));
        }
    }
}
