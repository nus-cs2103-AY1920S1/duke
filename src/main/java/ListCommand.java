/**
 * Simulates a list command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

public class ListCommand extends Command {

    /**
     * Executes the list command; prints the contents of the task list.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTaskList(taskList);
    }

}