/**
 * Handles listing of tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructor that takes in keywords and indentation for execution of listing function.
     * @param command First keyword entered by user determining command type
     * @param commandDetails Empty string
     * @param indent Constant indentation from start of line (formatting)
     */
    public ListCommand(String command, String commandDetails, String indent) {
        super(command, commandDetails, indent);
    }

    /**
     * Handles listing of tasks.
     * @param tasks Contains task list and operations to read list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Here are the tasks in your list:\n" + tasks.getListString());
    }
}
