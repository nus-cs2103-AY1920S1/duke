/**
 * Simulates a dummy command of the Duke System.
 * This is to be used to signify an error.
 * @author Fabian Chia Hup Peng
 */

public class DummyCommand extends Command {

    private static final String EMPTY_STRING = "";

    /**
     * A dummy execute method which does absolutely nothing.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     * @return The string representation of a successful dummy command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return EMPTY_STRING;
    }

}