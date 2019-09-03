/**
 * Handles exiting of programme
 */
public class ExitCommand extends Command {
    /**
     * Constructor that takes in keywords and indentation for execution of app-closing function
     * @param command First keyword entered by user determining command type
     * @param commandDetails Empty string
     * @param INDENT Constant indentation from start of line (formatting)
     */
    public ExitCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    /**
     * Handles application closing
     * @param tasks Contains task list and operations to delete from list
     * @param ui Handles user interaction
     * @param storage Updates new task list to file
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
    }
}
