package command;

import task.Task;
import task.TaskList;
import textual.Ui;

/**
 * Class representing a command to delete an item from the task list.
 */
public class DeleteCommand extends Command {
    private final int i;
    
    public DeleteCommand(int i) {
        this.i = i;
    }
    
    public void execute(TaskList tl, Ui ui) {
        try {
            Task t = tl.delete(i);
            ui.printMessage("Noted. I've removed this task:");
            ui.printMessage("  " + t);
            ui.printNumTasks(tl);
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\u2639 OOPS!!! Task index must be " +
                    "between 1 and " + tl.size() + ".");
        }
    }
}
