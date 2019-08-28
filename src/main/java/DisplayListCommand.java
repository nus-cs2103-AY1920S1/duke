/**
 * Encapsulates a user command to display the task list in full.
 */
public class DisplayListCommand extends Command {

    /**
     * Overridden method. Executes the display list command.
     * @param tasks list of tasks
     * @param ui user interface
     * @param storage storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");
        tasks.displayFullList();
    }
}

