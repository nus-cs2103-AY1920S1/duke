/**
 * Simulates an invalid command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

public class InvalidCommand extends Command {

    private static final String EMPTY_STRING = "";

    private String errorMessage;

    /**
     * Creates an InvalidCommand instance with the appropriate attributes.
     */
    public InvalidCommand() {
        this.errorMessage = EMPTY_STRING;
    }

    /**
     * Creates an InvalidCommand instance with the appropriate attributes.
     * @param errorMessage The string representation of the error message.
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Executes the invalid command; highlighting errors in input.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     * @return The string representation of a successful help command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.stringInvalidMessage(errorMessage);
    }

}