/**
 * Handles exiting of programme.
 */
public class ExitCommand extends Command {
    /**
     * Constructor that takes in keywords and indentation for execution of app-closing function.
     * @param command First keyword entered by user determining command type
     * @param commandDetails Empty string
     * @param indent Constant indentation from start of line (formatting)
     */
    public ExitCommand(String command, String commandDetails, String indent) {
        super(command, commandDetails, indent);
    }

    /**
     * Handles application closing.
     * @param tasks Contains task list and operations to delete from list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.exit();
    }
}
