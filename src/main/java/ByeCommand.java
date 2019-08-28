/**
 * Simulates a bye command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

public class ByeCommand extends Command {

    /**
     * Creates an ByeCommand instance with the appropriate attributes.
     */
    public ByeCommand() {
        isExit = true;
    }

    /**
     * Executes the bye command; stores the task list in the hard drive,
     * and prints the bye message.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        storage.storeList(taskList);

        ui.printGoodbyeMessage();
    }

}