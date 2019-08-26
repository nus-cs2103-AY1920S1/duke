
/**
 * Represents the command of exiting a Duke object run.
 * Contains the method calls to execute this command.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit from a Duke object's run process.
     * Task objects are saved into the Duke object's filepath and
     * the exit message is displayed.
     *
     * @param tasks TaskList object of the Duke object.
     * @param ui Duke object's Ui object to display the tasks.
     * @param storage Duke object's Storage object to access file for loading/saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        storage.loadTasksToFile(tasks);
        ui.showByeMessage();
    }

    /**
     * Indicates the exit condition of the running Duke object.
     *
     * @return To stop the Duke object from running, returns true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
