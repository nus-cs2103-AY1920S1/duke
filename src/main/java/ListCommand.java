/**
 * Represents the command of listing all Task objects from a Duke object's TaskList object.
 */
public class ListCommand extends Command {

    /**
     * Executes the displaying of all Task objects from a Duke object's TaskList object.
     *
     * @param tasks TaskList object with tasks to be displayed.
     * @param ui Duke object's Ui object to display the tasks.
     * @param storage Duke object's Storage object to access file for loading/saving tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.showListMessage(tasks);
    }

    /**
     * Indicates the exit condition of the running Duke object.
     *
     * @return DoneCommand is not the ExitCommand so it returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
