/**
 * Execute the command in Duke.
 */
public abstract class Command {
    private String command;

    /**
     *  Command constructor using string user input.
     * @param command user String input
     */
    public Command(String command) {
        this.command = command;
    }


    /**
     * Execute the command.
     * @param task Tasklist data
     * @param ui Ui interfaces and strings
     * @param storage stored file
     * @return a string to be displayed
     * @throws DukeException if any of raw values are invalid
     * @throws AssertionError if any of raw values are invalid
     */

    public abstract String execute(TaskList task, Ui ui, Storage storage) throws DukeException,AssertionError;

}