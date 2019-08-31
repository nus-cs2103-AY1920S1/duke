/**
 * Command to display all tasks in the list to user.
 */
public class ListCommand extends Command {

    /**
     * Executes the displaying of the task list.
     *
     * @param tasks Task list to display from.
     * @param ui User interface that assists with printing.
     * @param storage Unused.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasks(ui);
    }
}
