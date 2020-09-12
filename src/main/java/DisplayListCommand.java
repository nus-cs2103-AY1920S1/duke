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
        tasks.displayFullList();
    }

    @Override
    public String executeForGui(TaskList tasks, Ui ui, Storage storage) {
        return tasks.displayFullListForGui();
    }


}

