package command;

import task.TaskList;
import textual.Ui;

/**
 * Class representing a command to mark an item in the task list as done.
 */
public class DoneCommand extends Command {
    /**
     * Index of this command in the task list, where the first task is 1.
     */
    private final int i;
    
    public DoneCommand(int i) {
        this.i = i;
    }
    
    public void execute(TaskList tl, Ui ui) {
        try {
            tl.markDone(i);
            ui.printMessage("Nice! I've marked this task as done:");
            ui.printMessage("  " + tl.get(i));
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\u2639 OOPS!!! Task index must be " +
                    "between 1 and " + tl.size() + ".");
        }
    }
}
