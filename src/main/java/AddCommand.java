//@@author Parcly-Taxel
/**
 * Class representing a command to add a new task.
 */
public class AddCommand extends Command {
    /**
     * The task that will be added to the task list.
     */
    Task t;
    
    public AddCommand(Task t) {
        this.t = t;
    }
    
    public void execute(TaskList tl, Ui ui) {
        try {
            tl.add(t);
            ui.printMessage("Got it. I've added this task:");
            ui.printMessage("  " + t);
            ui.printNumTasks(tl);
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\u2639 OOPS!!! Task index must be " +
                    "between 1 and " + tl.size() + ".");
        }
    }
}
