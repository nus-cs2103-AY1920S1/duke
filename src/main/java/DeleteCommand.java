//@@author Parcly-Taxel
/**
 * Class representing a command to delete an item from the task list.
 */
public class DeleteCommand extends Command {
    /**
     * @see DoneCommand#i
     */
    int i;
    
    public DeleteCommand(int i) {
        this.i = i;
    }
    
    public void execute(TaskList tl, Ui ui) {
        try {
            ui.printMessage("Noted. I've removed this task:");
            ui.printMessage("  " + tl.delete(i));
            ui.printNumTasks(tl);
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\u2639 OOPS!!! Task index must be " +
                    "between 1 and " + tl.size() + ".");
        }
    }
}
