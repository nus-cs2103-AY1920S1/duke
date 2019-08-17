package command;

import task.Task;
import task.TaskList;
import textual.Ui;

/**
 * Class representing a command to add a new task.
 */
public class AddCommand extends Command {
    /**
     * The task that will be added to the task list.
     */
    private final Task t;
    
    public AddCommand(Task t) {
        this.t = t;
    }
    
    public void execute(TaskList tl, Ui ui) {
        tl.add(t);
        ui.printMessage("Got it. I've added this task:");
        ui.printMessage("  " + t);
        ui.printNumTasks(tl);
    }
}
