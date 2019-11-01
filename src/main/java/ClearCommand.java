import java.io.IOException;

/**
 * Represents a clear command.
 *
 * @author Michelle Yong
 */
public class ClearCommand extends Command {
    /**
     * Creates a clear command.
     */
    public ClearCommand() {}

    /**
     * Executes the clear command and tells the user all tasks has been cleared.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The message that the list has been cleared.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        taskList.clear();
        try {
            storage.updateTaskInFile(taskList.getList());
        } catch (IOException e) {
            System.out.println("IO Exception caught.");
        }

        return "The task list has been cleared.";
    }
}
