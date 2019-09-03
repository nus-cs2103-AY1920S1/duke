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
     * @return The string representation of a successful list command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.stringTaskList(taskList);
    }

}