package duke;

/**
 * Represents a command to exit Duke. An <code>ExitCommand</code> object corresponds to
 * an input from user to exit Duke e.g., bye
 */
public class ExitCommand extends Command {

    /**
     * Executes the command.
     * User interface prints out message to user and closes scanner.
     *
     * @param tasks Contains the task list .
     * @param ui Deals with interactions with the user.
     * @param storage Deals with loading tasks from the file and saving tasks in the file.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExitMessage();
        ui.closeScanner();
    }

    /**
     * Returns true for exit status.
     * If exit status is true, Duke stops operating.
     *
     * @return true.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Returns true if two instances of ExitCommand are equal.
     * Otherwise, returns false.
     *
     * @param o  An object.
     * @return boolean.
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
