/**
 * Represents a command which lists out the Tasks in the Tasklist.
 * @see TaskList
 * @see Task
 */

public class ListCommand extends Command {
    /**
     * Constructor for ListCommand
     * @param stringCommand String representation of the user input
     */
    public ListCommand(String stringCommand) {
        super(stringCommand);
    }

    /**
     * Executes the command by using the three arguments provided
     * @param taskList
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printListMessage();
        ui.printTaskList(taskList);
        taskList.list();
    }

    /**
     * Checks if Duke will end.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
