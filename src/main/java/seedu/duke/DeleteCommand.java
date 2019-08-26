package seedu.duke;

/**
 * Represents the command when user inputs 'delete'. A <code>DeleteCommand</code> object
 * can <code>execute</code> the command with checks for exception by deleting task from list.
 */
public class DeleteCommand extends Command {
    private String command;

    /**
     * Class constructor.
     *
     * @param command String command of 'delete'.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command after checking for exceptions.
     * Prints the message all the tasks in list with task information.
     *
     * @param tasks TaskList currently.
     * @param ui Ui initialized in <code>Duke</code> to interact with user.
     * @param storage Storage to append to data file after updating tasks.
     * @throws DukeException Exception for incorrect user input.
     * @throws Exception Exception for being unable to overwrite data file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        //deleting task
        ui.checkErrorForDeleteCommand(command, tasks);
        int curr = Parser.taskToDelete(command);
        tasks.remove(curr - 1);
        ui.printDeletedTaskMsg(tasks.get(curr - 1));
        ui.printNoOfTaskInList(tasks);
        //write 'tasks' into data file, overwriting all contents
        storage.writeFile(tasks);
    }

    /**
     * Returns false to continue Duke.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        //for testing purposes
        return "DeleteCommand";
    }
}
