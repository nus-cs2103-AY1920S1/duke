package seedu.duke;

/**
 * Represents the command when user inputs 'done'. A <code>MarkDoneCommand</code> object
 * can <code>execute</code> the command with checks for exception.
 */
public class MarkDoneCommand extends Command {
    private String command;

    /**
     * Constructor of MarkDoneCommand.
     *
     * @param command String command of 'done'.
     */
    public MarkDoneCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the command after checking for exceptions.
     * Prints the message with the task information and number of tasks in list.
     * Tick is printed for the task.
     *
     * @param tasks TaskList currently.
     * @param ui Ui initialized in <code>Duke</code> to interact with user.
     * @param storage Storage to overwrite data file after updating task as done.
     * @throws DukeException Exception for incorrect user input.t.
     * @throws Exception Exception for being unable to overwrite data file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        ui.checkMarkDoneError(command, tasks);
        int curr = Parser.taskToMarkDone(command);
        tasks.get(curr - 1).markAsDone();
        ui.printMarkDoneMsg(tasks.get(curr - 1));
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
        return "MarkDoneCommand";
    }
}