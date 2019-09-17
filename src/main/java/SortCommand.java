/**
 * Handles sorting of tasks, earliest deadline first
 */
public class SortCommand extends Command {
    private static final String SORT_TYPE_EARLIEST = "datetime";
    private static final String SORT_TYPE_COMPLETED = "completed";

    public SortCommand(String command, String commandDetails, String indent) {
        super(command, commandDetails, indent);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String responseMessage = "I have sorted your list.\n " + "Here are the tasks in your list:\n";
        if (commandDetails.trim().equals(SORT_TYPE_EARLIEST)) {
            return ui.printResponse(responseMessage + tasks.returnSortedTasksString(SORT_TYPE_EARLIEST));
        } else if (commandDetails.trim().equals(SORT_TYPE_COMPLETED)) {
            return ui.printResponse(responseMessage + tasks.returnSortedTasksString(SORT_TYPE_COMPLETED));
        } else {
            return ui.printError("OOPS!!! Do not recognise sort keyword. Try 'sort datetime' or 'sort "
                    + "completed'!");
        }
    }
}
