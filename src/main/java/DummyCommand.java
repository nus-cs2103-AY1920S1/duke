/**
 * Simulates a dummy command of the Duke System.
 * This is to be used to signify an error.
 * @author Fabian Chia Hup Peng
 */

public class DummyCommand extends Command {

    /**
     * A dummy execute method which does absolutely nothing.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
    }

}