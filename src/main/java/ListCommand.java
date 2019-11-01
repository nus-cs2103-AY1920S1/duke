/**
 * Represents a list command.
 *
 * @author Michelle Yong
 */
public class ListCommand extends Command {
    /**
     * Creates a list command with the description.
     */
    public ListCommand() {}

    /**
     * Executes the list command and shows all the tasks in the list.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message telling user about all the tasks in the list.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return taskList.showList();
    }
}